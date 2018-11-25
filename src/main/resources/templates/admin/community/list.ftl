<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>小区管理</title>
    <#include "../shared/cssFile.ftl" />
    <style type="text/css">
        .modal-body .form-group label {
            min-width: 110px;
        }
    </style>
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
                        Home</a> <a class="current" href="#">小区管理</a></div>
                </div>
                <hr/>
                <div class="container-fluid">
                    <form id="search_form" class="form-inline" style="margin-bottom: 10px;">
                        <div class="form-group">
                            <label>关键字查询：</label>
                            <input type="text" class="form-control" name="searchName" placeholder="小区名称">　
                        </div>
                        <div class="form-group">
                            <select id="searchCompanyID" name="searchCompanyID" class="form-control">
                                <option value="-1">--请选择公司--</option>
                            </select>　
                        </div>
                        <div class="form-group">
                            <select id="searchStationID" name="searchStationID" class="form-control">
                                <option value="-1">--请选择换热站--</option>
                            </select>　
                        </div>
                        <button id="search" type="button" class="btn btn-primary">
                            <span class="glyphicon glyphicon-search" aria-hidden="true"></span> 检索
                        </button>
                    </form>
                    <div id="maingrid" style="margin:0; padding:0"></div>

                    <!--html代码区域-->
                    <div class="modal fade" id="CreateModal" tabindex="-1" role="dialog" aria-labelledby="新建/编辑小区信息"
                         data-backdrop="static">
                        <div class="modal-dialog" style="width:600px;">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        &times;
                                    </button>
                                    <h4 class="modal-title" id="myModalLabel">小区信息</h4>
                                </div>
                                <form name="productInformationsForm" role="form" novalidate class="form-validation">
                                    <div class="modal-body">
                                        <input type="hidden" name="id" value="0"/>
                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>小区名称<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="form-control"
                                                   type="text" name="name" value="" required maxlength="50"
                                                   placeholder="小区名称"/>
                                            <span id="nameError" class="errorMessage"></span>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>所属公司<span class="required">  </span></label>
                                            <select id="companyID" name="companyID" class="form-control">
                                                <option value="-1">--请选择公司--</option>
                                            </select>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>所属换热站<span class="required">  </span></label>
                                            <select id="stationID" name="stationID" class="form-control">
                                                <option value="-1">--请选择换热站--</option>
                                            </select>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>所属行政区编号<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="text" name="regionalID" value="" maxlength="20"
                                                   placeholder="请输入行政区编号"/>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>采暖面积（平米)<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="text" name="heatingArea" value="" maxlength="20"
                                                   placeholder="请输入采暖面积"/>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>经度<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="text" name="mapX" value="" maxlength="20"
                                                   placeholder="请输入经度"/>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>纬度<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="text" name="mapY" value="" maxlength="20"
                                                   placeholder="请输入纬度"/>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>小区类型<span class="required">  </span></label>
                                            <select class="form-control" name="communityType">
                                                <option value="0">居建</option>
                                                <option value="1">公建</option>
                                                <option value="2">其他</option>
                                            </select>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>小区地址<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="text" name="communityAddress" value="" maxlength="200"
                                                   placeholder="请输入小区地址"/>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>热用户数量（户）<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="text" name="houseHoldCount" value="" maxlength="10"
                                                   placeholder="请输入热用户数量"/>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>状态<span class="required">  </span></label>
                                            <select class="form-control" name="status">
                                                <option value="1">正常</option>
                                                <option value="2">停用</option>
                                                <option value="3">删除</option>
                                            </select>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>备注<span class="required">  </span></label>
                                            <textarea name="remark" style="width:230px; display: inline; height: 50px" maxlength="200"
                                                      class="maxlength-handler form-control edited"></textarea>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">取消
                                        </button>
                                        <button type="button" class="btn btn-primary blue submit"><i
                                                class="fa fa-save"></i> <span>保存</span></button>
                                    </div>
                                </form>
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
<script src="/js/bussiness/communityManage.js" type="text/javascript"></script>
</body>
</html>
