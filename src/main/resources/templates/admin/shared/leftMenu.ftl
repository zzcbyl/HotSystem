<div class="col-md-3 left_col">
    <div class="left_col scroll-view">
        <div class="navbar nav_title" style="border: 0;">
            <a href="/admin/index" class="site_title"><i class="fa fa-paw"></i> <span
                    style="font-size:16px;">室内温度监测服务系统</span></a>
        </div>

        <div class="clearfix"></div>

        <!-- menu profile quick info -->
        <div class="profile clearfix">
            <div class="profile_pic">
                <img src="/images/img.jpg" alt="..." class="img-circle profile_img">
            </div>
            <div class="profile_info">
                <span>管理员</span>
                <h2 id="txtUserName"></h2>
            </div>
        </div>
        <!-- /menu profile quick info -->

        <br/>

        <!-- sidebar menu -->
        <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
            <div class="menu_section">
                <ul id="userMenu" class="nav side-menu">
                    <li>
                        <a href="#"><i class="fa fa-cog"></i> 系统管理 <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu">
                            <li><a href="/admin/company/list">热力公司管理</a></li>
                            <li><a href="/admin/heatexchangestation/list">换热站管理</a></li>
                            <li><a href="/admin/heatexchangerunit/list">换热机组管理</a></li>
                            <li><a href="/admin/community/list">小区管理</a></li>
                            <li><a href="/admin/building/list">楼房管理</a></li>
                            <li><a href="/admin/position/list">测温点管理</a></li>
                            <li><a href="/admin/employee/list">工作人员管理</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-home"></i> 住户信息管理 <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu">
                            <li><a href="/admin/customer/list">住户信息管理</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-wrench"></i> 设备管理 <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu">
                            <#--<li><a href="/admin/device/list">设备管理</a></li>-->
                            <li><a href="/admin/deviceinstall/list">设备安装记录</a></li>
                            <li><a href="/admin/controllist/list">控制命令管理</a></li>
                            <li><a href="/admin/alarmconfig/list">报警配置</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-magic"></i> 温度信息 <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu">
                            <li><a href="/admin/temprature/list">查看温度信息</a></li>
                        </ul>
                    </li>

                </ul>
            </div>
        </div>
        <!-- /sidebar menu -->
        <!-- /menu footer buttons -->
        <div class="sidebar-footer hidden-small">
            <a data-toggle="tooltip" data-placement="top" title="Settings">
                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
            </a>
            <a data-toggle="tooltip" data-placement="top" title="FullScreen">
                <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
            </a>
            <a data-toggle="tooltip" data-placement="top" title="Lock">
                <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
            </a>
            <a data-toggle="tooltip" data-placement="top" title="Logout" href="/Account/LoginOut">
                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
            </a>
        </div>
        <!-- /menu footer buttons -->
    </div>
</div>