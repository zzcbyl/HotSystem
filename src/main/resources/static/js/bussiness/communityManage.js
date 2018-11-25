var maingrid;
var stationJson = '';
(function () {
    $(function () {
        //设置提示框的样式
        toastr.options.positionClass = 'toast-center-center';
        var _modelService = app.createService('community');
        var _$modal = $('#CreateModal');
        var _$form = _$modal.find('form');
        //检索
        $("#search").click(function () {
            loadGrid();
        });

        var col = [
            {display: '编号', name: 'id', width: 80},
            {display: '小区名称', name: 'name', width: 150},
            {display: '所属换热站', name: 'stationName', width: 150},
            {display: '所属公司', name: 'companyName', width: 150},
            {display: '所属行政区编号', name: 'regionalID', width: 150},
            {display: '采暖面积', name: 'heatingArea', width: 150},
            {
                display: '小区类型', name: 'communityType', width: 150, render: function (rowData) {
                    if (rowData.communityType == "0") {
                        return "居建";
                    } else if (rowData.communityType == "1") {
                        return "公建 ";
                    } else {
                        return "其他";
                    }
                }
            },
            {display: '小区地址', name: 'communityAddress', width: 150},
            {display: '热用户数量', name: 'houseHoldCount', width: 150},
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
                url: "/admin/community/getPaging",//从服务端加载数据
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
                    _$modal.find("#myModalLabel").html("新建小区信息");
                    _$modal.find("input[name='id']").val('0');
                    _$modal.find("input[name='name']").val('');
                    _$modal.find("select[name='companyID']").val('-1');
                    _$modal.find("select[name='stationID']").val('-1');
                    _$modal.find("select[name='status']").val('1');
                    _$modal.find("input[name='regionalID']").val('');
                    _$modal.find("input[name='heatingArea']").val('');
                    _$modal.find("input[name='mapX']").val('0');
                    _$modal.find("input[name='mapY']").val('0');
                    _$modal.find("select[name='communityType']").val('0');
                    _$modal.find("input[name='communityAddress']").val('');
                    _$modal.find("input[name='houseHoldCount']").val('0');
                    _$modal.find("textarea[name='remark']").html('');
                    break;
                case "修改":
                    var selected = maingrid.getSelecteds();
                    if (selected.length != 1) {
                        toastr.warning('请选择一条数据!');
                        return;
                    }
                    _$modal.find("#myModalLabel").html("编辑小区信息");
                    _$modal.find("input[name='id']").val(selected[0].id);
                    _$modal.find("input[name='name']").val(selected[0].name);
                    _$modal.find("select[name='companyID']").val(selected[0].companyID);
                    bindStation("companyID", "stationID");
                    _$modal.find("select[name='stationID']").val(selected[0].stationID);
                    _$modal.find("select[name='status']").val(selected[0].status);
                    _$modal.find("input[name='regionalID']").val(selected[0].regionalID);
                    _$modal.find("input[name='heatingArea']").val(selected[0].heatingArea);
                    _$modal.find("input[name='mapX']").val(selected[0].mapX);
                    _$modal.find("input[name='mapY']").val(selected[0].mapY);
                    _$modal.find("select[name='communityType']").val(selected[0].communityType);
                    _$modal.find("input[name='communityAddress']").val(selected[0].communityAddress);
                    _$modal.find("input[name='houseHoldCount']").val(selected[0].houseHoldCount);
                    _$modal.find("textarea[name='remark']").html(selected[0].remark);
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

        $("#stationID").change(function () {
            checkName();
        });

        //检查小区名
        function checkName() {
            $('#nameError').html("");
            if ($.trim(_$modal.find("input[name='name']").val()) != '') {
                _modelService.getByName({
                        name: $.trim(_$modal.find("input[name='name']").val()),
                        stationID: _$modal.find("select[name='stationID']").val()
                    },
                    {id: _$modal.find("input[name='id']").val()}, function (data) {
                        if (data.data.result == 0) {
                            $('#nameError').html(data.data.message);
                        }
                    })
            }
        }

        //绑定换热站
        bindStation();

        //表单验证
        formValidator();

        function formValidator() {
            _$form.bootstrapValidator({
                message: 'This value is not valid',
                fields: {
                    name: {
                        message: '小区名称验证失败',
                        validators: {
                            notEmpty: {
                                message: '小区名称不能为空'
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

        //绑定分公司和换热站
        bindCompany();
        $("#searchCompanyID").change(function () {
            bindStation("searchCompanyID", "searchStationID");
        });
        $("#companyID").change(function () {
            bindStation("companyID", "stationID");
        });

        var _stationService = app.createService('heatexchangestation');
        _stationService.getAll({}, {"page": 1, "pagesize": 9999}, function (data) {
            stationJson = data;
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
