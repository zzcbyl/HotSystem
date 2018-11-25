/*用户的充值消费管理*/
var accountHistory={
    userId:0,
    userName:'',
    type:"-1"//-1全部类型，0充值，1消费
};
(function () {
    $(function () {
        var loginUser=getLoginUser();
        if(app.queryString("type"))
            accountHistory.type=app.queryString("type");
        accountHistory.userId=app.queryString("userId");
        accountHistory.userName=app.queryString("n");
        //普通用户登录(只能查看自己的记录）
        if(!loginUser.isManager)
        {
            accountHistory.userId=loginUser.id;
            accountHistory.userName=loginUser.realName;
            $("#btnAddNew").hide();//普通用户无法直接充值，只能去添加充值凭证。
        }
        else
        {
            $("#btnAddNew").show();
        }

        $("#currentUserAnchor").text(accountHistory.userName);

        $("[name='selType']").val(accountHistory.type);

        var _$ListTable = $('#ListTable');
        var _modelService = app.createService('accountHistory');
        
        var _$modal = $('#CreateModal');
        var _$form = _$modal.find('form');

        $("#btnAddNew").on("click", function () {

            _$modal.modal("show");
            _$modal.find("input[name='id']").val('');
            _$modal.find("input[name='userId']").val(accountHistory.userId);
            _$modal.find("#txtRealName").text(accountHistory.userName);
            _$modal.find("input[name='amount']").val('');
            _$modal.find("input[name='note']").val('');
        });

        _$form.validate();

        _$form.find('.submit').click(function (e) {
            e.preventDefault();
            if (!_$form.valid()) {
                return;
            }
            var model = _$form.serializeFormToObject();
/*            abp.ui.setBusy(_$modal);*/
            var manager=getLoginUser();
            model.createrId=manager.id;
            model.type=0;
            model.amount=model.amount*100;//单位换算，输入时的金额单位是元，保存时是分
            _modelService.create(model,function (data) {
                _$modal.modal('hide');
                getModelList();
            })
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
            defaultSorting:"ID Desc",
            //  multiSorting: true,  
            actions: {
                listAction:'/admin/accountHistory/getPaging'
            },

            fields: {
                //此处开始循环数据
                 
                id: {
                    title: '编号',
                    key: true,
                    list: true,
                    width:'3%'
                },
                type: {
                    title: '交易类型',
                    width: '3%',
                    display:function(data){
                        return data.record.type==0?'充值':'消费';
                    }
                },
                amount: {
                    title: '金额(元)',
                    width: '3%',
                    display:function(data){
                        return (data.record.type==0?'<span style="color:#ff0000;"> ':'<span style="color:#00ff00;"> ')+data.record.amount/100+'</span>';
                    }
                },
                account_balance: {
                    title: '操作后账号余额（元）',
                    width: '3%',
                    display:function(data){
                        return '<span style="color:#ff0000;"> '+data.record.accountBalance/100+'</span>';
                    }
                },
                creater_ID: {
                    title: '操作人员',
                    width: '4%',
                    display:function(data){
                        return data.record.createrName;
                    }
                },
                note: {
                    title: '备注',
                    width: '4%'

                },
                create_Time: {
                    title: '创建时间',
                    width: '4%',
                    display:function(data){
                        return moment(data.record.createTime).format('YYYY年MM月DD日, h:mm:ss a');
                    }
                },
            }
        });

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
                    userId: accountHistory.userId,
                    type:$("[name='selType']").val()
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

