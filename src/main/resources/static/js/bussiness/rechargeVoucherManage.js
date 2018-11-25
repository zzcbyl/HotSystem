//充值凭证逻辑
(function () {
    $(function () {
        var loginUser=getLoginUser();
        var userID=app.queryString('userID');
        var userName=app.queryString('n');
        if(!userID)
        {
            userID=loginUser.id;
            userName=loginUser.realName;
        }
        $("#currentUserAnchor").text(userName);

        var _$ListTable = $('#ListTable');
        var _modelService = app.createService('rechargeVoucher');
        
        var _$modal = $('#CreateModal');
        var _$form = _$modal.find('form');

        $("#btnAddNew").on("click", function () {

            _$modal.modal("show");
            
            _$modal.find("input[name='id']").val("");

            _$modal.find("input[name='userID']").val(userID);

            _$modal.find("input[name='account']").val("");
            _$modal.find("input[name='amount']").val('0');
            _$modal.find("input[name='voucherImgPath']").val("");

            _$modal.find("input[name='note']").val("");
        });

        _$form.validate();

        _$form.find('.submit').click(function (e) {
            e.preventDefault();
            if (!_$form.valid()) {
                return;
            }
            var model = _$form.serializeFormToObject();
            model.amount=model.amount*100;//元=》分
            if (!model.id) {
                _modelService.create(model,function (data) {
                    _$modal.modal('hide');
                    getModelList();
                })
            }
            else 
            {
                _modelService.update(
                model,function (data) {
                        _$modal.modal('hide');
                        getModelList();
                    }
                );
            }
        });
       
        _$modal.on('shown.bs.modal', function () {
            _$modal.find('input:not([type=hidden]):first').focus();
        });
    
        _$ListTable.jtable({
            pageList: "normal",
            title:'',
            paging: true,
            //selecting: true,
            //selectingCheckboxes :true,
            pageSizes: [10,20],
            pageSize:10,
            sorting: true,
            defaultSorting:"id Desc",
            //  multiSorting: true,  
            actions: {
                listAction:'/admin/rechargeVoucher/getPaging'
            },

            fields: {
                //此处开始循环数据
                id: {
                    title: '自增ID',
                    width: '5%',
                    key:true,
                    display:function(data){
                        return data.record.id;
                    }
                },
                userID: {
                    title: '用户id',
                    width: '5%',
                    key:false,
                    display:function(data){
                        return data.record.userID;
                    }
                },
                account: {
                    title: '微信或支付宝账号',
                    width: '5%',
                    key:false,
                    display:function(data){
                        return data.record.account;
                    }
                },
                amount: {
                    title: '充值金额(元)',
                    width: '5%',
                    key:false,
                    display:function(data){
                        return '<span style="color:#ff0000;">'+data.record.amount/100.0+'</span>';
                    }

                },
                voucherImgPath: {
                    title: '充值截图',
                    width: '5%',
                    key:false,
                    display:function(data){
                        return data.record.voucherImgPath;
                    }
                },
                state: {
                    title: '状态',
                    width: '5%',
                    key:false,
                    display:function(data){
                        return data.record.state==0?'<span style="color:#ff0000;">未处理</span>':data.record.state==1?'<span style="color:#00ff00;">已处理</span>':'<span style="color:#ddd;">已删除</span>';
                    }
                },
                note: {
                    title: '备注说明',
                    width: '5%',
                    key:false,
                    display:function(data){
                        return data.record.note;
                    }

                },
                createTime: {
                    title: '创建时间',
                    width: '5%',
                    key:false,
                    display:function(data){
                        return moment(data.record.createTime).format('YYYY年MM月DD日, h:mm:ss a');
                    }

                },
                actions: {
                    title:'操作',
                    sorting: false,
                    display: function (data) {
                        var $span = $('<span style="display:flex;flex-direction:row;justify-content:center;"></span>');
                        //编辑

                        $('<button class="btn btn-primary btn-xs" title="编辑"><i class="fa fa-edit">编辑</i></button>')
                            .appendTo($span)
                            .click(function () {
                                EditModel(data.record);
                            });

                        if(loginUser.isManager) {

                            //处理
                            $('<button class="btn btn-primary btn-xs" title="已处理"><i class="fa fa-edit">已处理</i></button>')
                                .appendTo($span)
                                .click(function () {
                                    Dealed(data.record);
                                });
                        }
                        //删除

                        $('<button class="btn btn-danger btn-xs" title="删除"><i class="fa fa-times">删除</i></button>')
                            .appendTo($span)
                            .click(function () {
                                deleteModel(data.record);
                            });

                        return $span;
                    }
                },
            }
        });

        function Dealed(model)
        {
            _modelService.update({id:model.id,state:1},function (data){
                getModelList();
            },function (err) {
                alert(err);
            });
        }

         function EditModel(model)
        {
            _$modal.off('show.bs.modal').on('show.bs.modal', function (){
            _$modal.find("input[name='id']").val(model.id);
                _$modal.find("input[name='userID']").val(model.userID);
                _$modal.find("input[name='account']").val(model.account);
                _$modal.find("input[name='amount']").val(model.amount/100.0);
                _$modal.find("input[name='voucherImgPath']").val(model.voucherImgPath);
                _$modal.find("input[name='note']").val(model.note);
                _$modal.find("#voucherImgPathShow").attr("src",model.voucherImgPath);
            });
            _$modal.modal("show");
        }


        //刷新表格信息
        $("#ButtonReload").click(function () {
            getModelList();
        });

        //模糊查询功能
        function getModelList(reload) {
            
            if (reload) {
                _$ListTable.jtable('reload');
            } else {
                _$ListTable.jtable('load', {
                     userID:userID
                    //type:$("[name='selType']").val()
                });
            }
        }

        //
        //删除当前product实体
        function deleteModel(model) {
            if (confirm('确定要删除该数据吗？'))
            {
                _modelService.delete({
                        id: model.id
                    },
                    function(data){
                        getModelList(true);
                    },
                    function(error){

                    });
            }
        }

        //搜索
        $('#btnSearch').click(function (e) {
            e.preventDefault();
            e.stopPropagation();
            getModelList();
        });
        getModelList();
    });
})();

//打开产品默认图片上传窗口
function showDefaultImageUploadForm(colName) {

    $("#UploadImageModal").modal("show");
    $("#UploadImageModal #uploadImgBtn").off("click").on("click",function (e) {
        app.uploadImg(e, function (imgUrl) { $("#"+colName).val(imgUrl); $("#"+colName+"Show").attr("src", imgUrl); },
            function (err) {
                 alert(JSON.stringify(err));
            });
    });
}
