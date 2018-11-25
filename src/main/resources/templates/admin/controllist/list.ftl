<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>控制命令管理</title>
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
                    <div id="breadcrumb"><a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>
                        Home</a> <a class="current" href="#">控制命令管理</a></div>
                </div>
                <hr/>
                <div class="container-fluid">
                    <form id="search_form" class="form-inline" style="margin-bottom: 10px;">
                        <div class="form-group">
                            <label>查询：</label>
                            <#--<input type="text" class="form-control" name="searchName" placeholder="小区名称">　-->
                        </div>
                        <div class="form-group">
                            <select id="searchRemoteCode" name="searchRemoteCode" class="form-control">
                                <option value="-1">--请选择命令代码--</option>
                            </select>　
                        </div>
                        <div class="form-group">
                            <select id="searchExecuteStatus" name="searchExecuteStatus" class="form-control">
                                <option value="-1">--请选择执行状态--</option>
                                <option value="1">已执行</option>
                                <option value="0">未执行</option>
                            </select>　
                        </div>
                        <button id="search" type="button" class="btn btn-primary">
                            <span class="glyphicon glyphicon-search" aria-hidden="true"></span> 检索
                        </button>
                    </form>
                    <div id="maingrid" style="margin:0; padding:0"></div>

                    <!--html代码区域-->
                    <div class="modal fade" id="CreateModal" tabindex="-1" role="dialog" aria-labelledby="新建/编辑控制命令"
                         data-backdrop="static">
                        <div class="modal-dialog" style="width:800px;">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        &times;
                                    </button>
                                    <h4 class="modal-title" id="myModalLabel">控制命令</h4>
                                </div>
                                <form name="productInformationsForm" role="form" novalidate class="form-validation">
                                    <div class="modal-body">
                                        <input type="hidden" name="id" value="0"/>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>范围类型</label>
                                            <select id="rangeType" name="rangeType" class="form-control">
                                                <option value="1">指定设备</option>
                                                <option value="2">小区</option>
                                                <option value="3">换热站</option>
                                                <option value="4">所有设备</option>
                                            </select>
                                        </div>

                                        <div id="rangeObject" class="form-group form-md-line-input form-md-floating-label">
                                            <label>范围对象编号</label>
                                            <select id="rangeObjectID" name="rangeObjectID" class="form-control">
                                            </select>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>控制代码</label>
                                            <select id="remoteCode" name="remoteCode" class="form-control">
                                            </select>
                                        </div>

                                        <input type="hidden" id="name" name="name" value="0"/>
                                        <input type="hidden" id="deviceColumn" name="deviceColumn" value=""/>
                                        <#--<div class="form-group form-md-line-input form-md-floating-label">
                                            <label>说明<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="text" id="name" name="name" value="" maxlength="500"/>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>操作字段<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="text" id="deviceColumn" name="deviceColumn" value="" maxlength="500"/>
                                        </div>-->

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>参数值<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="text" id="note" name="note" value="" required maxlength="10"/>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>状态<span class="required">  </span></label>
                                            <select class="form-control" name="status">
                                                <option value="1">正常</option>
                                                <option value="2">停用</option>
                                                <option value="3">删除</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                        <button type="button" class="btn btn-primary blue submit"><i
                                                class="fa fa-save"></i> <span>保存</span></button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                <#--执行记录列表-->
                    <div class="modal fade" id="RecordModal" tabindex="-1" role="dialog" aria-labelledby="设备执行列表"
                         data-backdrop="static">
                        <div class="modal-dialog" style="width:730px;">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        &times;
                                    </button>
                                    <h4 class="modal-title" id="recordModalTitle">设备执行列表</h4>
                                </div>
                                <div class="modal-body" style="padding: 10px;">
                                    <div id="recordGrid" style="margin:0; padding:0"></div>
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
<script src="/js/bussiness/controllistManage.js" type="text/javascript"></script>
</body>
</html>
