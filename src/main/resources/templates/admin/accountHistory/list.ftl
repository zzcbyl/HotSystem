<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>账户管理</title>
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
                    <div id="breadcrumb"> <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a class="current" href="#">账户管理</a> <a class="current" href="#" id="currentUserAnchor"></a></div>
                    <div class="row-fluid clearfix">
                        <div class="col-md-10">
                            <h1 id="h1CategoryName">账户管理</h1>
                        </div>
                        <div class="col-md-2" style="margin:20px 0 0px;text-align:right;">
                            <button id="btnAddNew" class="btn btn-primary blue"><i class="fa fa-plus"></i>充值</button>
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
                                           交易类型： <select name="selType">
                                                <option value="-1">全部</option>
                                                <option value="0">充值</option>
                                                <option value="1">消费</option>
                                            </select>
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
                        <div class="modal fade"  id="CreateModal" tabindex="-1" role="dialog" aria-labelledby="充值" data-backdrop="static">
                            <div class="modal-dialog" style="width:800px;">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h4 class="modal-title" id="myModalLabel">充值</h4>
                                    </div>
                                    <form name="productInformationsForm" role="form" novalidate class="form-validation">
                                        <div class="modal-body">
                                            <input type="hidden" name="id" value="0" />
                                            <input type="hidden" name="userId" value="0" />
                                            <div class="form-group form-md-line-input form-md-floating-label">
                                                <label>用户名<span class="required">  </span></label>
                                                <span id="txtRealName">张三</span>
                                            </div>
                                            <div class="form-group form-md-line-input form-md-floating-label">
                                                <label>充值金额（单位：元）<span class="required">  </span></label>
                                                <input style="width:80%;" class="maxlength-handler form-control edited" type="text" name="amount" value="" required  maxlength="128" />
                                            </div>
                                            <div id="passwordContainer" class="form-group form-md-line-input form-md-floating-label">
                                                <label>备注<span class="required">  </span></label>
                                                <textarea style="width:80%;height:50px" class="maxlength-handler form-control edited"  name="note" required  maxlength="500" >
                                                </textarea>
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
<script src="/js/bussiness/accountManage.js"  type="text/javascript"></script>
</body>
</html>