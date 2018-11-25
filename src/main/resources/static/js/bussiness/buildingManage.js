var maingrid;
var stationJson = '';
var communityJson = '';
(function () {
    $(function () {
        //设置提示框的样式
        toastr.options.positionClass = 'toast-center-center';
        var _modelService = app.createService('building');
        var _$modal = $('#CreateModal');
        var _$form = _$modal.find('form');
        //检索
        $("#search").click(function () {
            loadGrid();
        });

        var col = [
            {display: '编号', name: 'id', width: 80},
            {display: '楼房名称', name: 'name', width: 100},
            {display: '楼号', name: 'buildingNo', width: 70},
            {display: '楼层数', name: 'floorNumber', width: 70},
            {display: '楼高度', name: 'height', width: 80},
            {display: '所属小区', name: 'communityName', width: 150},
            {display: '所属换热站', name: 'stationName', width: 150},
            {display: '所属公司', name: 'companyName', width: 150},
            {display: '采暖面积', name: 'heatingArea', width: 100},
            {display: '建造年份', name: 'buildYear', width: 90},
            {display: '建筑结构', name: 'buildingStructure', width: 100},
            {display: '建筑类型', name: 'buildingType', width: 100},
            {
                display: '状态', name: 'status', width: 100, render: function (rowData) {
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
                url: "/admin/building/getPaging",//从服务端加载数据
                parms: $('#search_form').serializeArray(),//这里是关键，传递搜索条件的参数  serializeArray是jquery自带的吧form转json传递的方法
                page: 1,
                checkbox: false,
                rownumbers: true,
                pageSize: 20,
                usePager: true,
                width: '99%',
                height: '98%',
                toolbar: PostToolbarOptions
            });
        }

        function PostToolbarBtnItemClick(item) {
            switch (item.text) {
                case "添加":
                    _$modal.modal("show");
                    _$modal.find("#myModalLabel").html("新建楼房信息");
                    _$modal.find("input[name='id']").val('0');
                    _$modal.find("input[name='name']").val('');
                    _$modal.find("input[name='buildingNo']").val('');
                    _$modal.find("input[name='lati1']").val('0');
                    _$modal.find("input[name='longi1']").val('0');
                    _$modal.find("input[name='lati2']").val('0');
                    _$modal.find("input[name='longi2']").val('0');
                    _$modal.find("input[name='height']").val('0');
                    _$modal.find("input[name='floorNumber']").val('');
                    _$modal.find("select[name='companyID']").val('-1');
                    _$modal.find("select[name='stationID']").val('-1');
                    _$modal.find("select[name='communityID']").val('-1');
                    _$modal.find("select[name='status']").val('1');

                    _$modal.find("input[name='heatingArea']").val('0');
                    _$modal.find("input[name='buildYear']").val('');
                    _$modal.find("input[name='buildingStructure']").val('');
                    _$modal.find("input[name='energySaving']").prop("checked", false);
                    _$modal.find("input[name='measure']").prop("checked", false);
                    _$modal.find("input[name='separateControl']").prop("checked", false);
                    _$modal.find("input[name='energyConsumptionType']").val('');
                    _$modal.find("input[name='ngasstandardID']").val('0');
                    _$modal.find("input[name='buildingType']").val('');
                    _$modal.find("input[name='heatMeteringTransformed']").prop("checked", false);
                    _$modal.find("input[name='wallinSulationTransformed']").prop("checked", false);
                    _$modal.find("input[name='heatMeteringDate']").val('');
                    _$modal.find("input[name='wallinSulationDate']").val('');
                    _$modal.find("input[name='address']").val('');
                    _$modal.find("textarea[name='remark']").val('');
                    break;
                case "修改":
                    var selected = maingrid.getSelecteds();
                    if (selected.length != 1) {
                        toastr.warning('请选择一条数据!');
                        return;
                    }
                    _$modal.find("#myModalLabel").html("编辑楼房信息");
                    _$modal.find("input[name='id']").val(selected[0].id);
                    _$modal.find("input[name='name']").val(selected[0].name);
                    _$modal.find("input[name='buildingNo']").val(selected[0].buildingNo);
                    _$modal.find("select[name='companyID']").val(selected[0].companyID);
                    bindStation("companyID", "stationID");
                    _$modal.find("select[name='stationID']").val(selected[0].stationID);
                    bindCommunity("stationID", "communityID");
                    _$modal.find("select[name='communityID']").val(selected[0].communityID);
                    _$modal.find("input[name='floorNumber']").val(selected[0].floorNumber);
                    _$modal.find("input[name='lati1']").val(selected[0].lati1);
                    _$modal.find("input[name='longi1']").val(selected[0].longi1);
                    _$modal.find("input[name='lati2']").val(selected[0].lati2);
                    _$modal.find("input[name='longi2']").val(selected[0].longi2);
                    _$modal.find("input[name='height']").val(selected[0].height);
                    _$modal.find("select[name='status']").val(selected[0].status);

                    _$modal.find("input[name='heatingArea']").val(selected[0].heatingArea);
                    _$modal.find("input[name='buildYear']").val(selected[0].buildYear);
                    _$modal.find("input[name='buildingStructure']").val(selected[0].buildingStructure);
                    if (selected[0].energySaving == 1) {
                        _$modal.find("input[name='energySaving']").prop("checked", true);
                    } else {
                        _$modal.find("input[name='energySaving']").prop("checked", false);
                    }
                    if (selected[0].measure == 1) {
                        _$modal.find("input[name='measure']").prop("checked", true);
                    } else {
                        _$modal.find("input[name='measure']").prop("checked", false);
                    }
                    if (selected[0].separateControl == 1) {
                        _$modal.find("input[name='separateControl']").prop("checked", true);
                    } else {
                        _$modal.find("input[name='separateControl']").prop("checked", false);
                    }
                    _$modal.find("input[name='energyConsumptionType']").val(selected[0].energyConsumptionType);
                    _$modal.find("input[name='ngasstandardID']").val(selected[0].ngasstandardID);
                    _$modal.find("input[name='buildingType']").val(selected[0].buildingType);
                    if (selected[0].heatMeteringTransformed == 1) {
                        _$modal.find("input[name='heatMeteringTransformed']").prop("checked", true);
                    } else {
                        _$modal.find("input[name='heatMeteringTransformed']").prop("checked", false);
                    }
                    if (selected[0].wallinSulationTransformed == 1) {
                        _$modal.find("input[name='wallinSulationTransformed']").prop("checked", true);
                    } else {
                        _$modal.find("input[name='wallinSulationTransformed']").prop("checked", false);
                    }
                    _$modal.find("input[name='heatMeteringDate']").val(selected[0].heatMeteringDate == null ? "" : new Date(selected[0].heatMeteringDate).format('Y-m-d'));
                    _$modal.find("input[name='wallinSulationDate']").val(selected[0].wallinSulationDate == null ? "" : new Date(selected[0].wallinSulationDate).format('Y-m-d'));
                    _$modal.find("input[name='address']").val(selected[0].address);
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

        _$modal.find("input[name='name']").blur(function () {
            checkName();
        })

        $("#communityID").change(function () {
            checkName();
        });

        //检查楼房名
        function checkName() {
            $('#nameError').html("");
            if ($.trim(_$modal.find("input[name='name']").val()) != '') {
                _modelService.getByName({
                        name: $.trim(_$modal.find("input[name='name']").val()),
                        stationID: _$modal.find("select[name='stationID']").val(),
                        communityID: _$modal.find("select[name='communityID']").val()
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
                    name: {
                        message: '楼房名称验证失败',
                        validators: {
                            notEmpty: {
                                message: '楼房名称不能为空'
                            }
                        }
                    },
                    buildingNo: {
                        validators: {
                            notEmpty: {
                                message: '楼号不能为空　'
                            }
                        }
                    },
                    stationID: {
                        message: '换热站验证失败!',
                        validators: {
                            notEmpty: {
                                message: '换热站不能为空'
                            },
                            callback: {
                                message: '请选择换热站',
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
                        message: '小区验证失败!',
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
                    lati1: {
                        validators: {
                            notEmpty: {
                                message: '维度1不能为空'
                            }
                        }
                    },
                    longi1: {
                        validators: {
                            notEmpty: {
                                message: '经度1不能为空'
                            }
                        }
                    },
                    lati2: {
                        validators: {
                            notEmpty: {
                                message: '维度2不能为空'
                            }
                        }
                    },
                    longi2: {
                        validators: {
                            notEmpty: {
                                message: '经度2不能为空'
                            }
                        }
                    },
                    floorNumber: {
                        validators: {
                            notEmpty: {
                                message: '楼层数不能为空'
                            },
                            regexp: {
                                regexp: /^([0-9]+)$/,
                                message: '楼层数只能输入正整数'
                            }
                        }
                    },
                    height: {
                        validators: {
                            notEmpty: {
                                message: '楼高度不能为空'
                            },
                            regexp: {
                                regexp: /^([0-9]+(\.\d+)?|0\.\d+)$/,
                                message: '楼高度只能输入正浮点数'
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
                _modelService.update(
                    model, function (data) {
                        if (data.data.result == 1) {
                            _$modal.modal('hide');
                            maingrid.loadData();
                            toastr.success('修改成功!');
                        }
                        else {
                            $('#nameError').html(data.data.message);
                        }
                    }
                );
            }
        });

        _$modal.on('hidden.bs.modal', function () {
            _$form.data('bootstrapValidator').destroy();
            _$form.data('bootstrapValidator', null);
            $('#nameError').html("");
            formValidator();
        });

        _$modal.find("input[name='heatMeteringDate']").datetimepicker({
            format: 'yyyy-mm-dd',
            language: 'zh-CN',
            weekStart: 1,
            todayBtn: 1,//显示‘今日’按钮
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,  //Number, String. 默认值：0, 'hour'，日期时间选择器所能够提供的最精确的时间选择视图。
            forceParse: 0
        });
        _$modal.find("input[name='wallinSulationDate']").datetimepicker({
            format: 'yyyy-mm-dd',
            language: 'zh-CN',
            weekStart: 1,
            todayBtn: 1,//显示‘今日’按钮
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,  //Number, String. 默认值：0, 'hour'，日期时间选择器所能够提供的最精确的时间选择视图。
            forceParse: 0
        });

        //绑定分公司和换热站
        bindCompany();
        $("#searchCompanyID").change(function () {
            bindStation("searchCompanyID", "searchStationID");
            bindCommunity("searchStationID", "searchCommunityID");
        });
        $("#companyID").change(function () {
            bindStation("companyID", "stationID");
            bindCommunity("stationID", "communityID");
            checkName();
        });

        //绑定小区
        $("#searchStationID").change(function () {
            bindCommunity("searchStationID", "searchCommunityID");
        });
        $("#stationID").change(function () {
            bindCommunity("stationID", "communityID");
            checkName();
        });

        var _stationService = app.createService('heatexchangestation');
        _stationService.getAll({}, {"page": 1, "pagesize": 9999}, function (data) {
            stationJson = data;
        });

        var _communityService = app.createService('community');
        _communityService.getAll({}, {"page": 1, "pagesize": 9999}, function (data) {
            communityJson = data;
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

function bindCommunity(stationIDStr, communityIDStr) {
    //绑定换热站
    $("#" + communityIDStr).empty();
    $("#" + communityIDStr).append("<option value=\"-1\">--请选择小区--</option>");

    var stationId = $("#" + stationIDStr).val();
    if (parseInt(stationId) > 0 && communityJson != '') {
        var parentCommunity = JSON.parse(communityJson);
        if (parentCommunity.Rows.length > 0) {
            for (var i = 0; i < parentCommunity.Rows.length; i++) {
                if (parentCommunity.Rows[i].stationID == stationId && parentCommunity.Rows[i].status == 1)
                    $("#" + communityIDStr).append("<option value='" + parentCommunity.Rows[i].id + "'>" + parentCommunity.Rows[i].name + "</option>");
            }
        }
    }
}
