//安装位置逻辑
var maingrid;
var _$modal;
var _$form;
var stationJson = '';
var unitJson = '';
var communityJson = '';
var buildingJson = '';

(function () {
    $(function () {
        var _modelService = app.createService('position');
        _$modal = $('#CreateModal');
        _$form = _$modal.find('form');

        //设置提示框的样式
        toastr.options.positionClass = 'toast-center-center';
        //检索
        $("#search").click(function () {
            loadGrid();
        });

        var col = [
            {
                display: '测温点编号', name: 'id', width: 80, render: function (rowData) {
                    return "<a class='custom-a-style' href='javascript:void(0);' alt='点击查看温度' onclick='showTemp(" + rowData.id + ")'>" + rowData.id + "</a>";
                }
            },
            /* {
                 display: '设备编号', name: 'deviceSN', width: 90, render: function (rowData) {
                     if (rowData.deviceID == 0) {
                         return "-";
                     } else {
                         return rowData.deviceSN;
                     }
                 }
             },*/
            {
                display: '最新室内温度', name: 'temp1', width: 90, render: function (rowData) {
                    if (rowData.deviceID == 0) {
                        return "-";
                    } else {
                        return rowData.temp1 == 0 ? "0" : rowData.temp1;
                    }
                }
            },
            /*{
                display: '最新设备温度', name: 'temp2', width: 90, render: function (rowData) {
                    if (rowData.deviceID == 0) {
                        return "-";
                    } else {
                        return rowData.temp2 == 0 ? "0" : rowData.temp2;
                    }
                }
            },
            {
                display: '最新信号强度', name: 'level', width: 90, render: function (rowData) {
                    if (rowData.deviceID == 0) {
                        return "-";
                    } else {
                        return rowData.level == 0 ? "0" : rowData.level;
                    }
                }
            },*/
            {
                display: '最新采集时间', name: 'collectTime', width: 140, render: function (rowData) {
                    if (rowData.deviceID == 0) {
                        return "-";
                    } else {
                        return rowData.collectTime == null ? "" : new Date(rowData.collectTime).format('Y-m-d H:i:s');
                    }
                }
            },
            {display: '所属换热机组', name: 'heatExchangerUnitNumber', width: 160},
            {display: '所在小区', name: 'communityName', width: 130},
            {display: '楼号', name: 'buildingName', width: 70},
            {display: '单元号', name: 'subNumber', width: 60},
            {display: '楼层', name: 'floorNumber', width: 50},
            {display: '门牌号', name: 'apartNumber', width: 60},
            {display: '房间名称', name: 'roomName', width: 70},
            {display: '安装位置', name: 'exactPosition', width: 90},
            {display: '住户姓名', name: 'customerName', width: 70},
            {
                display: '住户位置', name: 'sideRoom', width: 70, render: function (rowData) {
                    if (rowData.sideRoom == 0) {
                        return "顶层";
                    } else if (rowData.sideRoom == 1) {
                        return "底层";
                    } else if (rowData.sideRoom == 1) {
                        return "中间位置";
                    } else {
                        return "边角";
                    }
                }
            },
            {display: '住户编号', name: 'houseHoldID', width: 80},
            {display: '房间/房屋面积', name: 'roomArea', width: 100},
            {
                display: '采暖方式', name: 'heatingType', width: 80, render: function (rowData) {
                    if (rowData.heatingType == 1) {
                        return "散热器";
                    } else {
                        return "地暖";
                    }
                }
            },
            {
                display: '状态', name: 'status', width: 80, render: function (rowData) {
                    if (rowData.status == "2") {
                        return "<span style='color: red'>停用</span>";
                    } else if (rowData.status == "3") {
                        return "<span style='color: red'>已删除</span>";
                    } else {
                        return "<span style='color: green'>正常</span>";
                    }
                }
            }
        ];
        var barcol = [
            {text: "添加", click: PostToolbarBtnItemClick, icon: 'add'},
            {text: "修改", click: PostToolbarBtnItemClick, icon: 'edit'},
            {text: "删除", click: PostToolbarBtnItemClick, icon: 'delete'}
        ];
        PostToolbarOptions = {
            items: barcol,
        };
        loadGrid();

        function loadGrid() {
            maingrid = $("#maingrid").ligerGrid({
                columns: col,
                dataAction: 'server',
                url: "/admin/position/getPaging",//从服务端加载数据
                parms: $('#search_form').serializeArray(),//这里是关键，传递搜索条件的参数  serializeArray是jquery自带的吧form转json传递的方法
                page: 1,
                checkbox: false,
                rownumbers: true,
                pageSize: 20,
                usePager: true,
                width: '99%',
                height: '98%',
                fixedCellHeight: false,
                toolbar: PostToolbarOptions
            });
        }

        function PostToolbarBtnItemClick(item) {
            switch (item.text) {
                case "添加":
                    _$modal.modal("show");
                    _$modal.find("#myModalLabel").html("新建测温点");
                    _$modal.find("input[name='id']").val('0');
                    _$modal.find("input[name='customerID']").val('0');
                    _$modal.find("input[name='customerName']").val('');
                    _$modal.find("input[name='lati']").val('0');
                    _$modal.find("input[name='longi']").val('0');
                    _$modal.find("input[name='height']").val('0');
                    _$modal.find("select[name='heatExchangerUnitID']").val('-1');
                    _$modal.find("select[name='communityID']").val('-1');
                    _$modal.find("select[name='buildingID']").val('-1');
                    _$modal.find("input[name='subNumber']").val('');
                    _$modal.find("input[name='floorNumber']").val('');
                    _$modal.find("input[name='apartNumber']").val('');
                    _$modal.find("input[name='exactPosition']").val('');
                    _$modal.find("select[name='sideRoom']").val(0);
                    _$modal.find("input[name='roomArea']").val('');
                    _$modal.find("input[name='status']").val('1');

                    _$modal.find("select[name='distance']").val('近');
                    _$modal.find("input[name='houseHoldID']").val('');
                    _$modal.find("input[name='heatingEnteryID']").val('');
                    _$modal.find("select[name='heatingType']").val(1);
                    _$modal.find("textarea[name='remark']").val('');

                    break;
                case "修改":
                    var selected = maingrid.getSelecteds();
                    if (selected.length != 1) {
                        toastr.warning('请选择一条数据!');
                        return;
                    }
                    _$modal.find("#myModalLabel").html("编辑测温点");
                    _$modal.find("input[name='id']").val(selected[0].id);
                    _$modal.find("input[name='customerID']").val(selected[0].customerID);
                    _$modal.find("input[name='customerName']").val(selected[0].customerName);
                    _$modal.find("input[name='lati']").val(selected[0].lati);
                    _$modal.find("input[name='longi']").val(selected[0].longi);
                    _$modal.find("input[name='height']").val(selected[0].height);
                    _$modal.find("select[name='companyID']").val(selected[0].companyID);
                    bindStation("companyID", "stationID");
                    _$modal.find("select[name='stationID']").val(selected[0].stationID);
                    bindHeatExchangeUnit("stationID", "heatExchangerUnitID");
                    _$modal.find("select[name='heatExchangerUnitID']").val(selected[0].heatExchangerUnitID);
                    bindCommunity("stationID", "communityID");
                    _$modal.find("select[name='communityID']").val(selected[0].communityID);
                    bindBuilding("communityID", "buildingID");
                    _$modal.find("select[name='buildingID']").val(selected[0].buildingID);
                    _$modal.find("input[name='subNumber']").val(selected[0].subNumber);
                    _$modal.find("input[name='floorNumber']").val(selected[0].floorNumber);
                    _$modal.find("input[name='apartNumber']").val(selected[0].apartNumber);
                    _$modal.find("select[name='roomName']").val(selected[0].roomName);
                    _$modal.find("input[name='exactPosition']").val(selected[0].exactPosition);
                    _$modal.find("select[name='sideRoom']").val(selected[0].sideRoom);
                    _$modal.find("input[name='roomArea']").val(selected[0].roomArea);
                    _$modal.find("select[name='status']").val(selected[0].status);

                    _$modal.find("select[name='distance']").val(selected[0].distance);
                    _$modal.find("input[name='houseHoldID']").val(selected[0].heatingEnteryID);
                    _$modal.find("input[name='heatingEnteryID']").val(selected[0].heatingEnteryID);
                    _$modal.find("select[name='heatingType']").val(selected[0].heatingType);
                    _$modal.find("textarea[name='remark']").val(selected[0].remark);

                    _$modal.modal("show");
                    break;
                case "查看":
                    break;
                case "删除":
                    var selected = maingrid.getSelecteds();
                    if (selected.length != 1) {
                        toastr.warning('请选择一条数据!');
                        return;
                    }

                    if (confirm('确定要删除该数据吗？')) {
                        _modelService.delete({
                                id: selected[0].id
                            },
                            function (data) {
                                maingrid.loadData();
                            },
                            function (error) {

                            });
                    }
                    break;
            }
        }

        _$modal.find("input[name='customerName']").blur(function () {
            checkRepeat();
        })

        $("#communityID").change(function () {
            checkRepeat();
        });
        $("#buildingID").change(function () {
            checkRepeat();
        });
        _$modal.find("input[name='subNumber']").blur(function () {
            checkRepeat();
        })
        _$modal.find("input[name='floorNumber']").blur(function () {
            checkRepeat();
        })
        _$modal.find("input[name='apartNumber']").blur(function () {
            checkRepeat();
        })
        _$modal.find("input[name='roomName']").blur(function () {
            checkRepeat();
        })

        //检查重复
        function checkRepeat() {
            $('#nameError').html("");
            if (_$modal.find("select[name='communityID']").val() != "-1"
                && _$modal.find("select[name='buildingID']").val() != '-1'
                && $.trim(_$modal.find("input[name='subNumber']").val()) != ''
                && $.trim(_$modal.find("input[name='floorNumber']").val()) != ''
                && $.trim(_$modal.find("input[name='apartNumber']").val()) != ''
                && $.trim(_$modal.find("input[name='roomName']").val()) != '') {
                _modelService.getByName({
                        communityID: _$modal.find("select[name='communityID']").val(),
                        buildingID: _$modal.find("select[name='buildingID']").val(),
                        subNumber: $.trim(_$modal.find("input[name='subNumber']").val()),
                        floorNumber: $.trim(_$modal.find("input[name='floorNumber']").val()),
                        apartNumber: $.trim(_$modal.find("input[name='apartNumber']").val()),
                        roomName: $.trim(_$modal.find("input[name='roomName']").val())
                    },
                    {id: _$modal.find("input[name='id']").val()}, function (data) {
                        if (data.data.result == 0) {
                            $('#nameError').html(data.data.message);
                        }
                    })
            }
        }

        //表单验证
        formValidator();

        function formValidator() {
            _$form.bootstrapValidator({
                message: 'This value is not valid',
                fields: {
                    lati: {
                        message: '维度验证失败',
                        validators: {
                            notEmpty: {
                                message: '维度不能为空'
                            }
                        }
                    },
                    longi: {
                        message: '经度验证失败',
                        validators: {
                            notEmpty: {
                                message: '经度不能为空'
                            }
                        }
                    },
                    height: {
                        message: '高度验证失败',
                        validators: {
                            notEmpty: {
                                message: '高度不能为空'
                            }
                        }
                    },
                    heatExchangerUnitID: {
                        message: '换热机组验证失败',
                        validators: {
                            notEmpty: {
                                message: '换热机组不能为空'
                            },
                            callback: {
                                message: '请选择换热机组',
                                callback: function (value, validator) {
                                    if (value == -1) {
                                        return false;
                                    } else {
                                        return true;
                                    }
                                }
                            }
                        }
                    },
                    communityID: {
                        message: '小区验证失败',
                        validators: {
                            notEmpty: {
                                message: '小区不能为空'
                            },
                            callback: {
                                message: '请选择小区',
                                callback: function (value, validator) {
                                    if (value == -1) {
                                        return false;
                                    } else {
                                        return true;
                                    }
                                }
                            }
                        }
                    },
                    buildingID: {
                        message: '楼号验证失败',
                        validators: {
                            notEmpty: {
                                message: '楼号不能为空'
                            },
                            callback: {
                                message: '请选择楼号',
                                callback: function (value, validator) {
                                    if (value == -1) {
                                        return false;
                                    } else {
                                        return true;
                                    }
                                }
                            }
                        }
                    },
                    subNumber: {
                        message: '单元号验证失败',
                        validators: {
                            notEmpty: {
                                message: '单元号不能为空'
                            }
                        }
                    },
                    floorNumber: {
                        message: '楼层验证失败',
                        validators: {
                            notEmpty: {
                                message: '楼层不能为空'
                            }
                        }
                    },
                    apartNumber: {
                        message: '门牌号验证失败',
                        validators: {
                            notEmpty: {
                                message: '门牌号不能为空'
                            }
                        }
                    },
                    roomArea: {
                        message: '房间面积验证失败',
                        validators: {
                            regexp: {
                                regexp: /^([0-9]+(\.\d+)?|0\.\d+)$/,
                                message: '房间面积只能输入正浮点数'
                            }
                        }
                    }
                }
            });
        }

        //表单提交
        _$form.find('.submit').click(function (e) {
            e.preventDefault();
            _$form.data('bootstrapValidator').validate();
            if (!_$form.data('bootstrapValidator').isValid()) {
                return;
            }
            if ($.trim($('#nameError').html()) != "") {
                return;
            }
            var model = _$form.serializeFormToObject();
            if (model.id == 0) {
                model.createrID = getLoginUser().id;
                _modelService.create(model, function (data) {
                    if (data.data.result == 1) {
                        _$modal.modal('hide');
                        maingrid.loadData();
                    }
                    else {
                        $('#nameError').html(data.data.message);
                    }
                })
            }
            else {
                _modelService.update(model, function (data) {
                    if (data.data.result == 1) {
                        _$modal.modal('hide');
                        maingrid.loadData();
                        toastr.success('修改成功!');
                    }
                    else {
                        $('#nameError').html(data.data.message);
                    }
                });
            }
        });

        _$modal.on('hidden.bs.modal', function () {
            _$form.data('bootstrapValidator').destroy();
            _$form.data('bootstrapValidator', null);
            $('#nameError').html("");
            formValidator();
        });

        //绑定房间
        bindRoomName();

        //绑定公司
        bindCompany();
        $("#searchCompanyID").change(function () {
            bindStation("searchCompanyID", "searchStationID");
        });
        $("#companyID").change(function () {
            bindStation("companyID", "stationID");
        });

        //绑定换热机组和小区
        $("#searchStationID").change(function () {
            bindHeatExchangeUnit("searchStationID", "searchHeatExchangerUnitID");
            bindCommunity("searchStationID", "searchCommunityID");
        });
        $("#stationID").change(function () {
            bindHeatExchangeUnit("stationID", "heatExchangerUnitID");
            bindCommunity("stationID", "communityID");
        });

        //绑定楼房
        $("#searchCommunityID").change(function () {
            bindBuilding("searchCommunityID", "searchBuildingID");
        });
        $("#communityID").change(function () {
            bindBuilding("communityID", "buildingID");
        });

        var _stationService = app.createService('heatexchangestation');
        _stationService.getAll({}, {"page": 1, "pagesize": 9999}, function (data) {
            stationJson = data;
        });

        var _heuService = app.createService('heatexchangerunit');
        _heuService.getAll({}, {"page": 1, "pagesize": 9999}, function (data) {
            unitJson = data;
        });

        var _communityService = app.createService('community');
        _communityService.getAll({}, {"page": 1, "pagesize": 9999}, function (data) {
            communityJson = data;
        });

        var _buildingService = app.createService('building');
        _buildingService.getAll({}, {"page": 1, "pagesize": 9999}, function (data) {
            buildingJson = data;
        });

        $("#customerName").autosuggest({
            url: '/admin/customer/getAutoSearch',
            method: 'post',
            queryParamName: 'name',
            firstSelected: true,
            minLength: 1,
            onSelect: function (ele) {
                _$modal.find("input[name='customerID']").val(ele.data('id'));
            }
        });
    });
})();


function bindCompany() {
    //绑定分公司
    var _companyService = app.createService('company');
    _companyService.getAll({"searchParentID": -2}, {"page": 1, "pagesize": 20}, function (data) {
        var parentCompany = JSON.parse(data);
        if (parentCompany.Rows.length > 0) {
            $("#searchCompanyID").empty();
            $("#companyID").empty();
            $("#searchCompanyID").append("<option value=\"-1\">--请选择公司--</option>");
            $("#companyID").append("<option value=\"-1\">--请选择公司--</option>");
            for (var i = 0; i < parentCompany.Rows.length; i++) {
                if (parentCompany.Rows[i].status == 1) {
                    $("#searchCompanyID").append("<option value='" + parentCompany.Rows[i].id + "'>" + parentCompany.Rows[i].name + "</option>");
                    $("#companyID").append("<option value='" + parentCompany.Rows[i].id + "'>" + parentCompany.Rows[i].name + "</option>");
                }
            }
        }
    });
}

function bindStation(companyIDStr, stationIDStr) {
    //绑定换热站
    $("#" + stationIDStr).empty();
    $("#" + stationIDStr).append("<option value=\"-1\">--请选择换热站--</option>");

    var companyId = $("#" + companyIDStr).val();
    if (parseInt(companyId) > 0 && stationJson != '') {
        var parentStation = JSON.parse(stationJson);
        if (parentStation.Rows.length > 0) {
            for (var i = 0; i < parentStation.Rows.length; i++) {
                if (parentStation.Rows[i].companyID == companyId && parentStation.Rows[i].status == 1)
                    $("#" + stationIDStr).append("<option value='" + parentStation.Rows[i].id + "'>" + parentStation.Rows[i].name + "</option>");
            }
        }
    }
}


function bindHeatExchangeUnit(stationIDStr, heatExchangerUnitIDStr) {
    //绑定换热机组
    $("#" + heatExchangerUnitIDStr).empty();
    $("#" + heatExchangerUnitIDStr).append("<option value=\"-1\">--请选择换热机组--</option>");

    var stationID = $("#" + stationIDStr).val();
    if (parseInt(stationID) > 0 && unitJson != '') {
        var parentUnit = JSON.parse(unitJson);
        if (parentUnit.Rows.length > 0) {
            for (var i = 0; i < parentUnit.Rows.length; i++) {
                if (parentUnit.Rows[i].stationID == stationID)
                    $("#" + heatExchangerUnitIDStr).append("<option value='" + parentUnit.Rows[i].id + "'>" + parentUnit.Rows[i].unitNumber + "</option>");
            }
        }
    }
}

function bindCommunity(stationIDStr, communityIDStr) {
    //绑定小区
    $("#" + communityIDStr).empty();
    $("#" + communityIDStr).append("<option value=\"-1\">--请选择小区--</option>");
    var stationID = $("#" + stationIDStr).val();
    if (parseInt(stationID) > 0 && communityJson != '') {
        var parentCommunity = JSON.parse(communityJson);
        if (parentCommunity.Rows.length > 0) {
            for (var i = 0; i < parentCommunity.Rows.length; i++) {
                if (parentCommunity.Rows[i].stationID == stationID && parentCommunity.Rows[i].status == 1)
                    $("#" + communityIDStr).append("<option value='" + parentCommunity.Rows[i].id + "'>" + parentCommunity.Rows[i].name + "</option>");
            }
        }
    }
}

function bindBuilding(communityIDStr, buildingIDStr) {
    //绑定楼房
    $("#" + buildingIDStr).empty();
    $("#" + buildingIDStr).append("<option value=\"-1\">--请选择楼号--</option>");

    var communityId = $("#" + communityIDStr).val();
    if (parseInt(communityId) > 0 && buildingJson != '') {
        var parentBuilding = JSON.parse(buildingJson);
        if (parentBuilding.Rows.length > 0) {
            for (var i = 0; i < parentBuilding.Rows.length; i++) {
                if (parentBuilding.Rows[i].communityID == communityId && parentBuilding.Rows[i].status == 1)
                    $("#" + buildingIDStr).append("<option value='" + parentBuilding.Rows[i].id + "'>" + parentBuilding.Rows[i].name + "</option>");
            }
        }
    }
}

function bindRoomName() {
    //绑定房间
    var _enumService = app.createService('enumlist');
    _enumService.getAll({'groupNo': 'RoomType'}, {"page": 1, "pagesize": 99}, function (data) {
        var roomNameEnum = JSON.parse(data);
        if (roomNameEnum.Rows.length > 0) {
            $("#roomName").empty();
            for (var i = 0; i < roomNameEnum.Rows.length; i++) {
                $("#roomName").append("<option value='" + roomNameEnum.Rows[i].value + "'>" + roomNameEnum.Rows[i].name + "</option>");
            }
        }
    });
}

function showTemp(positonID) {
    $("#templGrid").ligerGrid({
        columns: [
            {display: '室内温度', name: 'temp1', width: 80},
            /*{display: '设备温度', name: 'temp2', width: 80},*/
            //{display: '信号强度', name: 'level', width: 80},
            {
                display: '采集时间', name: 'addTime', width: 200, render: function (rowData) {
                    return rowData.addTime == null ? "" : new Date(rowData.addTime).format('Y-m-d H:i:s');
                }
            }
        ],
        dataAction: 'server',
        url: "/admin/temprature/getList",//从服务端加载数据
        parms: {deviceID: -1, positionID: positonID},
        page: 1,
        checkbox: false,
        rownumbers: true,
        pageSize: 20,
        usePager: true,
        width: '98%',
        height: '80%'
    });

    $("#tempModalTitle").html("近24小时测温点温度(测温点编号：" + positonID + ")");
    $('#TempModal').modal("show");
}



