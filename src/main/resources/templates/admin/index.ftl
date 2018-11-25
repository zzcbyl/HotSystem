<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>室内温度监测服务系统</title>
    <#include "shared/cssFile.ftl" />
    <style type="text/css">
        table{ border-left: 1px solid #0A0A0A; border-top: 1px solid #0A0A0A;}
        table td{ border-right: 1px solid #0A0A0A; border-bottom: 1px solid #0A0A0A;}
        .tab_warning { width: 100%;}
        .tab_warning table{ width: 100%; }
        .tab_warning table td{ height: 28px; line-height: 28px; text-align:center;}
        .redWarning { color: #FF0000; }
        .tab_data1{ width: 49%; float: left;}
        .tab_data1 table{ width: 100%;}
        .tab_data1 table td{ height: 47px; line-height: 47px; font-size: 16px; text-align: center;}
        .tab_data2{ width: 49%; float: left; margin-left: 2%;}
        .tab_data2 table{ width: 100%;}
        .tab_data2 table td{ height: 28px; line-height: 28px; font-size: 14px; text-align: center;}
    </style>
</head>
<body class="nav-sm">
<div class="container body">
    <div class="main_container">
        <#include "shared/topBar.ftl" />
        <#include "shared/leftMenu.ftl" />
        <div class="right_col" role="main" style="background: #fff; color: #333;">
            <div style="width: 80%; float: left;">
                <div style="width: 100%;">
                    <img src="/images/gis/u0.jpg" width="100%" />
                </div>
                <div style="width: 100%; margin: 20px 0;">
                    <div class="tab_data1">
                        <table>
                            <tr><td colspan="3" style="text-align: left;">
                                <button style="background:#fff;">实时数据</button> <button>历史数据</button></td></tr>
                            <tr><td>最高温度</td><td>最低温度</td><td>平均温度</td></tr>
                            <tr><td>25.6</td><td>19.8</td><td>20</td></tr>
                        </table>
                    </div>
                    <div class="tab_data2">
                        <table>
                            <tr><td>温度</td><td>安装点数量</td><td>占比</td></tr>
                            <tr><td>低于18度</td><td>200</td><td>2%</td></tr>
                            <tr><td>18~20度</td><td>23000</td><td>30%</td></tr>
                            <tr><td>20~22度</td><td>2043</td><td>20%</td></tr>
                            <tr><td>22~24度</td><td>4000</td><td>60%</td></tr>
                        </table>
                    </div>
                    <div style="clear: both;"></div>
                </div>
            </div>
            <div style="width: 18%; float: left; margin-left: 2%;">
                <div class="tab_warning">
                    <table>
                        <tr><td>小区名称</td><td>温度报警</td><td>日期</td></tr>
                        <tr><td>裕华小区</td><td class="redWarning">高温报警</td><td>2017-12-1</td></tr>
                        <tr><td>裕华小区</td><td>低温报警</td><td>2017-12-1</td></tr>
                        <tr><td>裕华小区</td><td>低温报警</td><td>2017-12-1</td></tr>
                        <tr><td>裕华小区</td><td>低温报警</td><td>2017-12-1</td></tr>
                        <tr><td>裕华小区</td><td class="redWarning">高温报警</td><td>2017-12-1</td></tr>
                        <tr><td>裕华小区</td><td>低温报警</td><td>2017-12-1</td></tr>
                        <tr><td>裕华小区</td><td class="redWarning">高温报警</td><td>2017-12-1</td></tr>
                        <tr><td>裕华小区</td><td>低温报警</td><td>2017-12-1</td></tr>
                    </table>
                </div>
                <div>
                    <div style="font-size: 18px; font-weight: bold; text-align: center; padding: 30px 0 15px;">温度预警</div>
                    <img src="/images/gis/u146.png" width="100%;">
                </div>
                <div>
                    <div style="font-size: 18px; font-weight: bold; text-align: center; padding: 30px 0 15px;">温度分布图</div>
                    <img src="/images/gis/u21.png" width="100%;">
                </div>
            </div>
            <br style="clear: both;" />
        </div>
        <#include "shared/footer.ftl" />
    </div>
</div>
<#include "shared/footerScript.ftl" />
</body>
</html>