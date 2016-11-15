/**
 * Created by company on 2016/10/10.
 */
$(function(){
    // 路径配置
    require.config({
        paths: {
            echarts: 'http://echarts.baidu.com/build/dist'
        }
    });

// 使用
    require(
        [
            'echarts',
            'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var myChart1 = ec.init(document.getElementById('main1'));
            var myChart2 = ec.init(document.getElementById('main2'));

            var option1 = {
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient : 'vertical',
                    x : 'left',
                    data:['普享标','出借计划']
                },
                toolbox: {
                    show : false,
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        magicType : {
                            show: true,
                            type: ['pie', 'funnel'],
                            option: {
                                funnel: {
                                    x: '25%',
                                    width: '50%',
                                    funnelAlign: 'center',
                                    max: 1548
                                }
                            }
                        },
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
//                    calculable : true,
                series : [
                    {
                        name:'访问来源',
                        type:'pie',
                        radius : ['50%', '70%'],
                        itemStyle : {
                            normal : {
                                label : {
                                    show : false
                                },
                                labelLine : {
                                    show : false
                                }
                            },
                        },
                        data:[
                            {value:335, name:'普享标'},
                            {value:310, name:'出借计划'}
                        ]
                    }
                ],
                title:{
                    text:'3673247.00\n出借总额（元）',
                    x:'center',
                    y:'center',
                    textStyle:{
                        color:'#929292',
                        width:''
                    }
                }
            };
            var option2 = {
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient : 'vertical',
                    x : 'left',
                    data:['普享标','出借计划','加息收益']
                },
                toolbox: {
                    show : false,
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        magicType : {
                            show: true,
                            type: ['pie', 'funnel'],
                            option: {
                                funnel: {
                                    x: '25%',
                                    width: '50%',
                                    funnelAlign: 'center',
                                    max: 1548
                                }
                            }
                        },
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
//                    calculable : true,
                series : [
                    {
                        name:'访问来源',
                        type:'pie',
                        radius : ['50%', '70%'],
                        itemStyle : {
                            normal : {
                                label : {
                                    show : false
                                },
                                labelLine : {
                                    show : false
                                }
                            },
                        },
                        data:[
                            {value:335, name:'普享标'},
                            {value:310, name:'出借计划'},
                            {value:200, name:'加息收益'}
                        ]
                    }
                ],
                title:{
                    text:'3673247.00\n收益总额（元）',
                    x:'center',
                    y:'center',
                    textStyle:{
                        color:'#929292',
                    }
                }
            };
            // 为echarts对象加载数据
            myChart1.setOption(option1);
            myChart2.setOption(option2);
        }
    );
})