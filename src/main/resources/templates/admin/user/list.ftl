<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户管理</title>
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
                    <div id="breadcrumb"> <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a class="current" href="#">用户管理</a></div>
                    <div class="row-fluid clearfix">
                        <div class="col-md-10">
                            <h1 id="h1CategoryName">用户管理</h1>
                        </div>
                        <div class="col-md-2" style="margin:20px 0 0px;text-align:right;">
                            <button id="btnAddNew" class="btn btn-primary blue"><i class="fa fa-plus"></i>添加用户</button>
                        </div>
                    </div>
                </div>
                <hr />
                <div class="container-fluid">
                    <div class="row-fluid">
                       <!-- 通用搜索功能  -->
                        <div class="portlet-title portlet-title-filter">
                            <div class="inputs inputs-full-width">
                                <div class="portlet-input">
                                    <form>
                                        <div class="row chat-message well" style="text-align:center;">
                                        <span class="input-box">
                                            <input style="width:300px;" name="txtRealName" id="txtRealName" placeholder="请输入姓名" value="" type="text">
                                        </span>
                                            <button id="btnSearch" class="btn btn-primary search-btn" type="submit"><i class="fa fa-search"></i> 搜索</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="portlet-body" style="margin:10px 0;padding:10px 0;">
                            <div id="ListTable"></div>
                        </div>
                        <!--html代码区域-->
                        <div class="modal fade"  id="CreateModal" tabindex="-1" role="dialog" aria-labelledby="用户信息编辑" data-backdrop="static">
                            <div class="modal-dialog" style="width:800px;">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h4 class="modal-title" id="myModalLabel">用户信息编辑</h4>
                                    </div>
                                    <form name="productInformationsForm" role="form" novalidate class="form-validation">
                                        <div class="modal-body">
                                            <input type="hidden" name="id" value="0" />
                                            <div class="form-group form-md-line-input form-md-floating-label">
                                                <label>账号<span class="required">  </span></label>
                                                <input style="width:80%;" class="maxlength-handler form-control edited" type="text" name="account" value="" required  maxlength="128" />
                                            </div>
                                            <div id="passwordContainer" class="form-group form-md-line-input form-md-floating-label">
                                                <label>密码<span class="required">  </span></label>
                                                <input style="width:80%;" class="maxlength-handler form-control edited" type="text" name="password" value="" required  maxlength="128" />
                                            </div>
                                            <div class="form-group form-md-line-input form-md-floating-label">
                                                <label>姓名<span class="required">  </span></label>
                                                <input style="width:80%;" class="maxlength-handler form-control edited" type="text" name="realName" value="" required  maxlength="128" />
                                            </div>
                                            <div class="form-group form-md-line-input form-md-floating-label">
                                                <label>联系电话<span class="required">  </span></label>
                                                <input style="width:80%;" class="maxlength-handler form-control edited" type="text" name="mobile" value=""  maxlength="128" />
                                            </div>
                                            <div class="form-group form-md-line-input form-md-floating-label">
                                                <label>QQ<span class="required">  </span></label>
                                                <input style="width:80%;" class="maxlength-handler form-control edited" type="text" name="qq" value=""  maxlength="128" />
                                            </div>
                                            <div class="form-group form-md-line-input form-md-floating-label">
                                                <label>微信<span class="required">  </span></label>
                                                <input style="width:80%;" class="maxlength-handler form-control edited" type="text" name="weixin" value=""  maxlength="128" />
                                            </div>
                                            <div class="form-group form-md-line-input form-md-floating-label">
                                                <label>可用时长（单位：天）<span class="required">  </span></label>
                                                <input style="width:80%;" class="maxlength-handler form-control edited" type="text" name="availableLength" value="" required  maxlength="500" />
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
<#include "../shared/footerScript.ftl" />
<script src="/js/bootstrap-maxlength.min.js"></script>
<script src="/js/app.js"></script>
<script src="/js/bussiness/userManage.js"  type="text/javascript"></script>
</body>
</html>