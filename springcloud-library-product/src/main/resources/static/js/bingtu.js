var chart = Highcharts.chart('container01', {
    chart: {
        type: 'pie',
        options3d: {
            enabled: true,
            alpha: 45
        }
    },
    title: {
        text: '各职位占比'
    },
    subtitle: {
        text: '员工职位'
    },
    plotOptions: {
        pie: {
            innerSize: 100,
            depth: 45
        }
    },
    series: [{
        name: '职位人数',
        data: [
            ['普通员工', 8],
            ['清洁工', 3],
            ['保安', 1],
            ['用户管理员', 6],
            ['图书管理员', 8],
            ['员工管理员', 4],
            ['财务管理员', 4],
            ['超级管理员', 1]
        ]
    }]
});

// ********************************************************************************
var chart = new Highcharts.Chart({
    chart: {
        renderTo: 'container02',
        type: 'column',
        options3d: {
            enabled: true,
            alpha: 15,
            beta: 15,
            depth: 50,
            viewDistance: 25
        }
    },
    title: {
        text: '月度出售账单'
    },
    subtitle: {
        text: '图书出售统计'
    },
    plotOptions: {
        column: {
            depth: 25
        }
    },
    series: [{
        name:'图例1',
        data: [29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4]
    }]
});
// 将当前角度信息同步到 DOM 中
/*var alphaValue = document.getElementById('alpha-value'),
    betaValue = document.getElementById('beta-value'),
    depthValue = document.getElementById('depth-value');
function showValues() {
    alphaValue.innerHTML = chart.options.chart.options3d.alpha;
    betaValue.innerHTML = chart.options.chart.options3d.beta;
    depthValue.innerHTML = chart.options.chart.options3d.depth;
}
// 监听 sliders 的变化并更新图表
$('#sliders input').on('input change', function () {
    chart.options.chart.options3d[this.id] = this.value;
    showValues();
    chart.redraw(false);
});
showValues();*/

