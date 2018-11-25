package com.fastrun.TempCollection.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.util.JSONPObject
//import com.auth0.jwt.internal.org.bouncycastle.asn1.ocsp.ResponseData
import com.fastrun.TempCollection.auth.AuthHelper
import com.fastrun.TempCollection.model.User
import com.fastrun.TempCollection.service.UserService
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.View
import java.util.*
import javax.annotation.Resource
import org.apache.tomcat.jni.SSL.setPassword
import com.fastrun.TempCollection.ResponseData
import com.fastrun.TempCollection.ResponsePagingData
import com.fastrun.TempCollection.auth.Audience
import com.fastrun.TempCollection.common.AESEncoder
import com.fastrun.TempCollection.service.ServiceTaskService
import jdk.nashorn.internal.parser.JSONParser
import org.springframework.web.bind.annotation.*
import java.util.HashMap
import com.fastrun.TempCollection.auth.JwtHelper
import com.fastrun.TempCollection.common.Encode
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import sun.security.x509.OIDMap.addAttribute
import org.apache.tomcat.jni.Lock.name
import org.springframework.util.ClassUtils
import java.io.*
import java.io.File.separator
import java.io.FileOutputStream
import java.io.BufferedOutputStream
import com.sun.xml.internal.ws.util.JAXWSUtils.getUUID
import java.io.FileInputStream
import java.io.IOException


@Controller
class UserController {
    @Resource
    var userService: UserService? = null

    @Resource
    var upload: UploadConfig? = null

    @GetMapping("/testDES")
    @ResponseBody
    fun des(@RequestParam data: String): ResponseData {
        var encode = AESEncoder.AESEncode(AESEncoder.salt, data)
        var decode = AESEncoder.AESDncode(AESEncoder.salt, encode!!)
        val re = ResponseData()
        re.putDataValue("encode", encode)
        re.putDataValue("decode", decode)
        return re
    }

    internal fun String.getExtensionName(): String {
        if (this != null && this.length > 0) {
            val dot = this.lastIndexOf('.')
            if (dot > -1 && dot < this.length - 1) {
                return this.substring(dot + 1)
            }
        }
        return this;
    }

    @PostMapping("/admin/user/upload")
    @ResponseBody
    fun uploadImg(@RequestParam(value = "file0") file: MultipartFile): ResponseData {
        val responseData = ResponseData()
        if (!file.isEmpty) {
            try {

                var savePath = saveImg(file, upload!!.location, upload!!.browserPath)
                responseData.putDataValue("imgPath", savePath)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
                return ResponseData.putErrValue(listOf("上传失败," + e.message))
            } catch (e: IOException) {
                e.printStackTrace()
                return ResponseData.putErrValue(listOf("上传失败," + e.message))
            }
        } else {
            return ResponseData.putErrValue(listOf("上传失败，未选择文件。"))
        }
        return responseData;
    }

    /**
     * 保存文件，直接以multipartFile形式
     * @param multipartFile
     * @param path 文件保存绝对路径
     * @return 返回文件名
     * @throws IOException
     */
    @Throws(IOException::class)
    fun saveImg(multipartFile: MultipartFile, path: String, browserPath: String): String {
        val file = File(path)
        if (!file.exists()) {
            file.mkdirs()
        }
        val fileInputStream = multipartFile.inputStream as FileInputStream
        val fileName = getUUID() + "." + multipartFile.originalFilename?.getExtensionName()
        val bos = BufferedOutputStream(FileOutputStream(path + File.separator + fileName))
        val bs = ByteArray(1024)
        var len: Int
        len = fileInputStream.read(bs)
        while (len != -1) {
            bos.write(bs, 0, len)
            len = fileInputStream.read(bs)
        }
        bos.flush()
        bos.close()
        return browserPath + fileName
    }

    @PostMapping("login1")
    @ResponseBody
    fun login(@RequestParam account: String, @RequestParam password: String, @RequestParam rememberMe: Boolean): ResponseData {
        var desPwd = AESEncoder.AESEncode(AESEncoder.salt, password)
        val user = userService?.findByAccount(account)
        if (user != null) {
            val responseData = ResponseData()
            if (user.password == desPwd) {
                if (user.state != 1 && user.state != 2) {
                    return ResponseData.putErrValue(listOf("账号已停用"))
                }
                var audience = Audience()
                // var token= AuthHelper().sign(user,1000*60*60*24*2) //两天的用户登录时间
                var jwtToken = JwtHelper.createJWT(user.realName,
                        user.account,
                        user.id.toString(),
                        user.type.toString(),
                        audience.clientId,
                        audience.name,
                        1000 * 60 * 60 * 24 * 2,
                        audience.base64Secret)
                jwtToken = Encode().Base64Encode(jwtToken)
                val token = "bearer:$jwtToken"

                responseData.putDataValue("token", token)
                user.password = "" //发送给客户端前清除密码
                responseData.putDataValue("user", user)
                return responseData
            }
            return ResponseData.putErrValue(listOf("用户名或者密码错误")) //密码错误
        }
        return ResponseData.putErrValue(listOf("用户名或者密码错误")) //用户用户不存在
    }


    @GetMapping("login1")
    @ResponseBody
    fun loginForm(@RequestParam(required = false, defaultValue = "") returnUrl: String): ModelAndView {
        return ModelAndView("login")
    }

    @GetMapping("user/changePassword")
    @ResponseBody
    fun changePassword(): ModelAndView {

        return ModelAndView("admin/user/changePassword")
    }

    @PostMapping("user/changePassword")
    @ResponseBody
    fun changePasswordPost(@RequestParam id: Long, @RequestParam orgPassword: String, @RequestParam newPassword: String): ResponseData {
        var res = ResponseData();
        var user = userService?.get(id)
        var orgPwdDecode = AESEncoder.AESDncode(AESEncoder.salt, user!!.password)
        if (orgPassword != orgPwdDecode) {
            res.putDataValue("Result", "Error")
            res.putDataValue("Message", "原密码错误")
        } else {
            var pwdEncode = AESEncoder.AESEncode(AESEncoder.salt, newPassword)
            var result = userService?.changPassword(user.id, pwdEncode!!)
            res.putDataValue("Result", "OK")
            res.putDataValue("Message", result)
        }
        return res

    }

    /*
    * 验证token是否有效
    * */
    @PostMapping("checklogin")
    @ResponseBody
    fun checkLogin(@RequestParam token: String, @RequestParam account: String): ResponseData {
        var res = ResponseData();
        var user = userService?.findByAccount(account)
        var tokenUser = AuthHelper().unsign(token, User::class.java)
        if (tokenUser != null && user != null && user.account == tokenUser.account && user.id == tokenUser.id && user.password == tokenUser.password) {
            res.putDataValue("isValid", true);
        } else {
            res.putDataValue("isValid", false);
        }
        return res;
    }

    @PostMapping("/admin/user/create")
    @ResponseBody
    fun createUserPost(@RequestBody user: User): ResponseData {
        val re = ResponseData()
        var existsAccount = userService?.findByAccount(user.account)
        if (existsAccount != null && existsAccount.id > 0) {
            re.error.add("系统中已存在相同的账号，请修改后重试！")
            return re
        }
        user.password = AESEncoder.AESEncode(AESEncoder.salt, user.password)!!
        var result = userService?.insertUser(user)
        re.putDataValue("result", result)
        return re
    }

    @PostMapping("/admin/user/update")
    @ResponseBody
    fun updateUser(@RequestBody user: User): ResponseData {
        val re = ResponseData()
        print("state:" + user.state + "=-===========================")
        var result = userService?.update(user)

        re.putDataValue("result", result)
        return re;
    }

    @GetMapping("/admin/getList")
    @ResponseBody
    fun getList(@RequestParam(defaultValue = "admin1", required = false) account: String): String {
        var items = userService?.findByAccount(account)

        val jsonMap = HashMap<String, Any>()
        jsonMap["Result"] = "OK"
        jsonMap["Records"] = items!!
        val mapper = ObjectMapper()
        var result = mapper.writeValueAsString(jsonMap)
        return result

    }

    @PostMapping("/admin/user/getPaging")
    @ResponseBody
    fun getPaging(@RequestParam(defaultValue = "", required = false) realName: String, @RequestParam(name = "jtStartIndex", defaultValue = "0", required = true) offset: Int, @RequestParam(name = "jtPageSize") pageSize: Int, @RequestParam(name = "jtSorting", defaultValue = "id desc", required = false) orderBy: String): String {
        var items = userService?.getPaging(realName, offset, pageSize, orderBy)
        var counts = userService?.getCount(realName)

        val jsonMap = HashMap<String, Any>()
        jsonMap["Result"] = "OK"
        jsonMap["Records"] = items!!
        jsonMap["TotalRecordCount"] = counts!!
        val mapper = ObjectMapper()
        var result = mapper.writeValueAsString(jsonMap)
        return result

        /* re.Records.addAll(items!!)

         re.TotalRecordCount=counts!!
         re.Result="OK"
         return re*/
    }


    @GetMapping("/admin/user/list")
    fun userList(model: Model): ModelAndView {
        //model.addAttribute("list",userService?.getAllPart())
        return ModelAndView("admin/user/list")
    }

    /*
    * 只修改状态
    * */
    @PostMapping("/admin/user/delete")
    @ResponseBody
    fun deleteUser(@RequestParam id: Int): ResponseData {
        val re = ResponseData()
        var result = userService?.delete(id)
        re.putDataValue("result", result)
        return re;
    }

/*    @GetMapping("createHtml")
    @ResponseBody
    fun createHtml():String {
        var list = userService?.getAll().orEmpty()
        var template =
                """
            <html>
            <body><table><tr><td>姓名</td><td>年龄</td><td>id</td></tr>
            """
        val template1 = """
             <tr><td>{#username}</td><td>{#age}</td><td></td></tr>
        """
        for (user in list)
        {
            template+="""
             <tr><td>${user.realName}</td><td>${user.mobile}</td><td>${user.id}</td></tr>
        """
        }
        template+="""</table>
            </body>
            </html>"""
        return template
    }*/


}