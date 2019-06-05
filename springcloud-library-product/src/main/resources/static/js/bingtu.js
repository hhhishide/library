function bingtu(){
    var chart = {
        type: 'pie',
        options3d: {
            enabled: true,
            alpha: 45,
            beta: 0
        }
    };
    var title = {
        text: '2014 年特定网站各浏览器占有率'
    };
    var tooltip = {
        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
    };

    var plotOptions = {
        pie: {
            allowPointSelect: true,
            cursor: 'pointer',
            depth: 35,
            dataLabels: {
                enabled: true,
                format: '{point.name}'
            }
        }
    };
    var series= [{
        type: 'pie',
        name: 'Browser share',
        data: [
            ['Firefox',   45.0],
            ['IE',       26.8],
            {
                name: 'Chrome',
                y: 12.8,
                sliced: true,
                selected: true
            },
            ['Safari',    8.5],
            ['Opera',     6.2],
            ['Others',   0.7]
        ]
    }];

    var json = {};
    json.chart = chart;
    json.title = title;
    json.tooltip = tooltip;
    json.plotOptions = plotOptions;
    json.series = series;
    $('#container').highcharts(json);
}