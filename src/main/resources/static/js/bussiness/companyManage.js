var maingrid;

(function () {
    $(function () {
        //设置提示框的样式
        toastr.options.positionClass = 'toast-center-center';

        var _modelService = app.createService('company');
        var _$modal = $('#CreateModal');
        var _$form = _$modal.find('form');

        //检索
        $("#search").click(function () {
            loadGrid();
        });

        var col = [
            {display: '编号', name: 'id', width: 80},
            {display: '公司名称', name: 'name', width: 150},
            {display: '地址', name: 'address', width: 250},
            {display: '联系人', name: 'contact', width: 80},
            {display: '联系方式', name: 'contactWay', width: 100},
            {
                display: '总公司', name: 'parentCompanyName', width: 150, render: function (rowData) {
                    if (rowData.parentCompanyName == '') {
                        return "无";
                    }
                    return rowData.parentCompanyName;
                }
            },
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
                url: "/admin/company/getPaging",//从服务端加载数据
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
                    _$modal.find("#myModalLabel").html("新建热力公司");
                    _$modal.find("input[name='id']").val('0');
                    _$modal.find("input[name='name']").val('');
                    _$modal.find("input[name='address']").val('');
                    _$modal.find("input[name='contact']").val('');
                    _$modal.find("input[name='contactWay']").val('');
                    _$modal.find("input[name='parentID']").val('0');
                    _$modal.find("input[name='status']").val('0');
                    _$modal.find("input[name='createTime']").val(Date());
                    break;
                case "修改":
                    var selected = maingrid.getSelecteds();
                    if (selected.length != 1) {
                        toastr.warning('请选择一条数据!');
                        return;
                    }
                    _$modal.find("#myModalLabel").html("编辑热力公司");
                    _$modal.find("input[name='id']").val(selected[0].id);
                    _$modal.find("input[name='name']").val(selected[0].name);
                    _$modal.find("input[name='address']").val(selected[0].address);
                    _$modal.find("input[name='contact']").val(selected[0].contact);
                    _$modal.find("input[name='contactWay']").val(selected[0].contactWay);
                    _$modal.find("select[name='parentID']").val(selected[0].parentID);
                    _$modal.find("select[name='status']").val(selected[0].status);
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
                                bindParentCompany();
                            },
                            function (error) {

                            });
                    }
                    break;
            }
        }

        _$modal.find("input[name='name']").blur(function () {
            $('#nameError').html("");
            if ($.trim($(this).val()) != '') {
                _modelService.getByName({name: $.trim($(this).val())}, {id: _$modal.find("input[name='id']").val()}, function (data) {
                    if (data.data.result == 0) {
                        $('#nameError').html(data.data.message);
                    }
                })
            }
        })

        //绑定总公司
        bindParentCompany();

        //表单验证
        formValidator();

        function formValidator() {
            _$form.bootstrapValidator({
                message: 'This value is not valid',
                fields: {
                    name: {
                        message: '公司名称验证失败',
                        validators: {
                            notEmpty: {
                                message: '公司名称不能为空'
                            }
                        }
                    },
                    address: {
                        validators: {
                            notEmpty: {
                                message: '公司地址不能为空　'
                            }
                        }
                    },
                    contact: {
                        validators: {
                            notEmpty: {
                                message: '联系人不能为空'
                            }
                        }
                    },
                    contactWay: {
                        validators: {
                            notEmpty: {
                                message: '联系方式不能为空'
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
                        bindParentCompany();
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

        function bindParentCompany() {
            //绑定查询下拉
            _modelService.getAll({"searchParentID": 0}, {"page": 1, "pagesize": 20}, function (data) {
                var parentCompany = JSON.parse(data);
                if (parentCompany.Rows.length > 0) {
                    $("#searchParentID").empty();
                    $("#parentID").empty();
                    $("#searchParentID").append("<option value=\"-1\">--请选择总公司--</option>");
                    $("#parentID").append("<option value=\"0\">无</option>");
                    for (var i = 0; i < parentCompany.Rows.length; i++) {
                        $("#searchParentID").append("<option value='" + parentCompany.Rows[i].id + "'>" + parentCompany.Rows[i].name + "</option>");
                        $("#parentID").append("<option value='" + parentCompany.Rows[i].id + "'>" + parentCompany.Rows[i].name + "</option>");
                    }
                }
            });
        }
    });

})();
