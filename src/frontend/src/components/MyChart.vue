<template>
  <div id="main" style="width: 800px; height: 400px"></div>
</template>

<script>
import * as echarts from "echarts";
export default {
  name: "MyChart",
  props: ["xAxisAttr", "yAxisVal"],
  data() {
    return {
      myChart: "",
      xAxis: this.xAxisAttr,
      yAxis: this.yAxisVal,
    };
  },
  watch: {
    xAxisAttr(newVal) {
      this.xAxis = newVal;
      this.chartChange();
    },
    yAxisVal(newVal) {
      this.yAxis = newVal;
      this.chartChange();
    },
  },
  methods: {
    chartChange() {
      let option = {
        title: {
          text: "Data",
        },
        tooltip: {},
        legend: {
          data: ["count"],
        },
        xAxis: {
          data: this.xAxis,
        },
        yAxis: {},
        series: [
          {
            barGap: "50%",
            barCategoryGap: "50%",
            name: "count",
            type: "bar",
            data: this.yAxis,
            itemStyle: {
              normal: {
                color: function (params) {
                  var colorList = [
                    "#c23531",
                    "#2f4554",
                    "#61a0a8",
                    "#d48265",
                    "#91c7ae",
                    "#749f83",
                    "#ca8622",
                  ];
                  return colorList[params.dataIndex % colorList.length];
                },
              },
            },
          },
        ],
      };
      this.myChart.setOption(option, true);
    },

    myEcharts() {
      this.myChart = echarts.init(document.getElementById("main"));
      let option = {
        title: {
          text: "Demonstration",
        },
        tooltip: {},
        legend: {
          data: ["count"],
        },
        xAxis: {
          data: this.xAxis,
        },
        yAxis: {},
        series: [
          {
            barGap: "50%",
            barCategoryGap: "50%",
            name: "count",
            type: "bar",
            data: this.yAxis,
            itemStyle: {
              normal: {
                color: function (params) {
                  var colorList = [
                    "#d87c7c",
                    "#919e8b",
                    "#d7ab82",
                    "#6e7074",
                    "#61a0a8",
                    "#efa18d",
                    "#787464",
                  ];
                  return colorList[params.dataIndex % colorList.length];
                },
              },
            },
          },
        ],
      };
      this.myChart.setOption(option);
      // console.log(this.xAxis);
    },
  },
  mounted() {
    this.myEcharts();
  },
};
</script>
