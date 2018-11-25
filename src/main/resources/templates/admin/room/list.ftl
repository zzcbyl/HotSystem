<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>房间信息管理</title>
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
                        Home</a> <a class="current" href="#">房间信息管理</a></div>
                </div>
                <hr/>
                <div class="container-fluid">
                    <form id="search_form" class="form-inline" style="margin-bottom: 10px;">
                        <div class="form-group">
                            <label>关键字查询：</label>
                            <input type="text" class="form-control" name="searchCustomerName" placeholder="住户姓名">　
                        </div>
                        <div class="form-group">
                            <select id="searchCommunityID" name="searchCommunityID" class="form-control">
                                <option value="-1">--请选择小区--</option>
                            </select>　
                        </div>
                        <div class="form-group">
                            <select id="searchBuildingID" name="searchBuildingID" class="form-control">
                                <option value="-1">--请选择楼号--</option>
                            </select>　
                        </div>

                        <button id="search" type="button" class="btn btn-primary">
                            <span class="glyphicon glyphicon-search" aria-hidden="true"></span> 检索
                        </button>
                    </form>
                    <div id="maingrid" style="margin:0; padding:0"></div>

                    <!--html代码区域-->
                    <div class="modal fade" id="CreateModal" tabindex="-1" role="dialog" aria-labelledby="新建/编辑房间信息"
                         data-backdrop="static">
                        <div class="modal-dialog" style="width:600px;">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        &times;
                                    </button>
                                    <h4 class="modal-title" id="myModalLabel">房间信息</h4>
                                </div>
                                <form name="productInformationsForm" role="form" novalidate class="form-validation">
                                    <div class="modal-body">
                                        <input type="hidden" name="id" value="0"/>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>用户业主编号<span class="required">  </span></label>
                                            <input type="hidden" name="customerID" value="0"/>
                                            <input style="width:80%;" class="maxlength-handler form-control edited"
                                                   type="text" name="customerName" value="" required maxlength="20"
                                                   placeholder="请输入住户姓名"/>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>维度<span class="required">  </span></label>
                                            <input style="width:80%;" class="maxlength-handler form-control edited"
                                                   type="text" name="lati" value="" required maxlength="10"/>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>经度<span class="required">  </span></label>
                                            <input style="width:80%;" class="maxlength-handler form-control edited"
                                                   type="text" name="longi" value="" required maxlength="10"/>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>高度<span class="required">  </span></label>
                                            <input style="width:80%;" class="maxlength-handler form-control edited"
                                                   type="text" name="hight" value="" required maxlength="10"/>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>所在小区<span class="required">  </span></label>
                                            <select id="communityID" name="communityID" class="form-control">
                                                <option value="-1">--请选择小区--</option>
                                            </select>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>楼号<span class="required">  </span></label>
                                            <select id="buildingID" name="buildingID" class="form-control">
                                                <option value="-1">--请选择楼号--</option>
                                            </select>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>单元号<span class="required">  </span></label>
                                            <input style="width:80%;" class="maxlength-handler form-control edited"
                                                   type="text" name="subNumber" value="" required maxlength="10"
                                                   placeholder="请输入单引号"/>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>楼层<span class="required">  </span></label>
                                            <input style="width:80%;" class="maxlength-handler form-control edited"
                                                   type="text" name="floorNumber" value="" required maxlength="10"
                                                   placeholder="请收入楼层"/>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>门牌号<span class="required">  </span></label>
                                            <input style="width:80%;" class="maxlength-handler form-control edited"
                                                   type="text" name="apartNumber" value="" required maxlength="10"
                                                   placeholder="请输入门牌号"/>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>房间名称<span class="required">  </span></label>
                                            <input style="width:80%;" class="maxlength-handler form-control edited"
                                                   type="text" name="roomName" value="" maxlength="10"
                                                   placeholder="请输入房间名称(客厅/卫生间/卧1/...)"/>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label class="checkbox-inline">
                                                <input type="checkbox" id="sideRoom" name="sideRoom" value="1"> 边户
                                            </label>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>房间/房屋面积<span class="required">  </span></label>
                                            <input style="width:80%;" class="maxlength-handler form-control edited"
                                                   type="text" name="roomArea" value="" maxlength="10"
                                                   placeholder="请输入房间面积"/>
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
                </div>
            </div>
        </div>
        <#include "../shared/footer.ftl" />
    </div>
</div>
<#include "../shared/footerScript.ftl" />
<script src="/js/bootstrap-maxlength.min.js"></script>
<script src="/js/app.js"></script>
<script src="/js/bussiness/roomManage.js" type="text/javascript"></script>
</body>
</html>
