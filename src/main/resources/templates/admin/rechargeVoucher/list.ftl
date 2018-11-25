<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>充值凭证管理</title>
    <#include "../shared/cssFile.ftl" />
</head>
<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <#include "../shared/topBar.ftl" />
        <#include "../shared/leftMenu.ftl" />
        <div class="right_col" role="main">
            <div id="content">
                <div id="content-header">
                    <div id="breadcrumb"> <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a class="current" href="#">充值凭证管理</a></div>
                    <div class="row-fluid clearfix">
                        <div class="col-md-10">
                            <h1 id="h1CategoryName">充值凭证管理</h1>
                        </div>
                        <div class="col-md-2" style="margin:20px 0 0px;text-align:right;">
                            <button id="btnAddNew" class="btn btn-primary blue"><i class="fa fa-plus"></i>新增</button>
                        </div>
                    </div>
                </div>
                <hr />
                <div class="container-fluid">
                    <div class="row-fluid">
                        <!-- 通用搜索功能 
                        <div class="portlet-title portlet-title-filter">
                            <div class="inputs inputs-full-width">
                                <div class="portlet-input">
                                    <form>
                                        <div class="row chat-message well" style="text-align:center;">
                                        <span class="input-box">
                                            
                                        </span>
                                            <button id="btnSearch" class="btn btn-primary search-btn" type="submit"><i class="fa fa-search"></i> 搜索</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        -->
                        <div class="portlet-body" style="margin:10px 0;padding:10px 0;">
                            <div id="ListTable"></div>
                        </div>
                        <!--html代码区域-->
                        <div class="modal fade"  id="CreateModal" tabindex="-1" role="dialog" aria-labelledby="新建/编辑充值凭证" data-backdrop="static">
                            <div class="modal-dialog" style="width:800px;">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h4 class="modal-title" id="myModalLabel">充值凭证</h4>
                                    </div>
                                    <form name="productInformationsForm" role="form" novalidate class="form-validation">
                                        <div class="modal-body">
                                            <input type="hidden" name="id" value="0" />
                                            <input type="hidden" name="userID" value="0" />
                                            <div class="form-group form-md-line-input form-md-floating-label">
                                                <label>充值金额(单位：元）<span class="required">  </span></label>
                                                <input style="width:80%;" class="maxlength-handler form-control edited" type="text" name="amount" value="" required  maxlength="500" />
                                            </div>
                                            <div class="form-group form-md-line-input form-md-floating-label">
                                                  <label>微信或支付宝账号<span class="required">  </span></label>
                                                  <input style="width:80%;" class="maxlength-handler form-control edited" type="text" name="account" value="" required  maxlength="500" />
                                            </div>
                                            <div class="form-group form-md-line-input form-md-floating-label">
                                                <label>充值截图<span style="border:solid 1px #888888;background-color:#efefef;cursor:pointer;padding:2px;" onclick="showDefaultImageUploadForm('voucherImgPath');">上传</span></label>
                                                <img src="" id="voucherImgPathShow" style="width:100px;" />
                                                <input type="hidden" id="voucherImgPath" name="voucherImgPath" value="" />
                                            </div>
                                            <div class="form-group form-md-line-input form-md-floating-label">
                                                  <label>备注说明<span class="required">  </span></label>
                                                  <input style="width:80%;" class="maxlength-handler form-control edited" type="text" name="note" value=""   maxlength="500" />
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                            <button type="button" class="btn btn-primary blue submit"><i class="fa fa-save"></i> <span>保存</span></button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <#include "../shared/footer.ftl" />
    </div>
</div>
<div class="modal fade" id="UploadImageModal" tabindex="-1" role="dialog" aria-labelledby="上传图片" data-backdrop="static">
    <div class='modal-dialog'>
        <div class='modal-content'>
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel1"> 上传图片</h4>
            </div>
            <div class='modal-body'>
                <div class='upload-picture'>

                    <form id="uploadImageForm" name="uploadImageForm">
                        <input type="file" name="fileToUpload1" id="fileToUpload1" multiple="multiple" />
                        <img id="selImg" src="" style="width:50px;" />
                        <progress id="progressBar" value="0" max="100"></progress>
                        <span id="percentage"></span>
                        <input id="uploadImgBtn" type="button" value="上传" />
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>
<#include "../shared/footerScript.ftl" />
<script src="/js/bootstrap-maxlength.min.js"></script>
<script src="/js/app.js"></script>
<script src="/js/bussiness/rechargeVoucherManage.js"  type="text/javascript"></script>
</body>
</html>
