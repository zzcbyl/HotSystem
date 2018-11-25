<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>楼房管理</title>
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
                        Home</a> <a class="current" href="#">楼房管理</a></div>
                </div>
                <hr/>
                <div class="container-fluid">
                    <form id="search_form" class="form-inline" style="margin-bottom: 10px;">
                        <div class="form-group">
                            <label>关键字查询：</label>
                            <input type="text" class="form-control" name="searchName" placeholder="楼房名称">　
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
                        <div class="form-group">
                            <select id="searchCommunityID" name="searchCommunityID" class="form-control">
                                <option value="-1">--请选择小区--</option>
                            </select>　
                        </div>
                        <button id="search" type="button" class="btn btn-primary">
                            <span class="glyphicon glyphicon-search" aria-hidden="true"></span> 检索
                        </button>
                    </form>
                    <div id="maingrid" style="margin:0; padding:0"></div>

                    <!--html代码区域-->
                    <div class="modal fade" id="CreateModal" tabindex="-1" role="dialog" aria-labelledby="新建/编辑楼房信息"
                         data-backdrop="static">
                        <div class="modal-dialog" style="width:600px;">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        &times;
                                    </button>
                                    <h4 class="modal-title" id="myModalLabel">楼房信息</h4>
                                </div>
                                <form name="productInformationsForm" role="form" novalidate class="form-validation">
                                    <div class="modal-body">
                                        <input type="hidden" name="id" value="0"/>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>楼房名称<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="text" name="name" value="" required maxlength="50"/>
                                            <span id="nameError" class="errorMessage"></span>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>楼号<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="text" name="buildingNo" value="" required maxlength="50"/>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>所属公司<span class="required">  </span></label>
                                            <select id="companyID" name="companyID" class="form-control">
                                                <option value="-1">--请选择公司--</option>
                                            </select>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>换热站<span class="required">  </span></label>
                                            <select id="stationID" name="stationID" class="form-control">
                                                <option value="-1">--请选择换热站--</option>
                                            </select>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>小区<span class="required">  </span></label>
                                            <select id="communityID" name="communityID" class="form-control">
                                                <option value="-1">--请选择小区--</option>
                                            </select>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>楼层数<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="text" name="floorNumber" value="" required maxlength="50"/>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>楼高度<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="text" name="height" value="" required maxlength="50"/>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>维度1<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="text" name="lati1" value="" required maxlength="50"/>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>经度1<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="text" name="longi1" value="" required maxlength="50"/>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>维度2<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="text" name="lati2" value="" required maxlength="50"/>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>经度2<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="text" name="longi2" value="" required maxlength="50"/>
                                        </div>

                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>采暖面积<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="text" name="heatingArea" value="" maxlength="10"/>
                                        </div>
                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>建造年份<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="text" name="buildYear" value="" maxlength="10"/>
                                        </div>
                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>建筑结构<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="text" name="buildingStructure" value="" maxlength="50"/>
                                        </div>
                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>　</label>
                                            <label style="text-align:left;">
                                                <input type="checkbox" id="energySaving" name="energySaving" value="1"> 节能建筑　
                                            </label>
                                            <label style="text-align:left;">
                                                <input type="checkbox" id="measure" name="measure" value="1"> 安装计量装置　
                                            </label>
                                            <label style="text-align:left;">
                                                <input type="checkbox" id="separateControl" name="separateControl" value="1"> 分户控制
                                            </label>
                                        </div>
                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>能耗分类<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="text" name="energyConsumptionType" value="" maxlength="30"/>
                                        </div>
                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>能耗分类ID<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="text" name="ngasstandardID" value="" maxlength="10"/>
                                        </div>
                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>建筑类型<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="text" name="buildingType" value="" maxlength="30"/>
                                        </div>
                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>　</label>
                                            <label style="text-align:left;">
                                                <input type="checkbox" id="heatMeteringTransformed" name="heatMeteringTransformed" value="1"> 热计量改造
                                            </label>
                                            <label style="text-align:left;">
                                                <input type="checkbox" id="wallinSulationTransformed" name="wallinSulationTransformed" value="1"> 墙体保温改造
                                            </label>
                                        </div>
                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>热计量改造年代<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="text" name="heatMeteringDate" value="" maxlength="10"/>
                                        </div>
                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>墙体保温改造年代<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="text" name="wallinSulationDate" value="" maxlength="10"/>
                                        </div>
                                        <div class="form-group form-md-line-input form-md-floating-label">
                                            <label>地址<span class="required">  </span></label>
                                            <input style="width:230px; display: inline;" class="maxlength-handler form-control edited"
                                                   type="text" name="address" value="" maxlength="100"/>
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
<script src="/js/bussiness/buildingManage.js" type="text/javascript"></script>
</body>
</html>
