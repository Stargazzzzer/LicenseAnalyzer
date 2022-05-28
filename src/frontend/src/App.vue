<template>
  <body style="background-color: #f8f5ef">
    <br /><br /><br /><br /><br /><br /><br /><br />
    <el-row type="flex" justify="center" align="middle">
      <el-col :span="4">
        <el-dropdown size="medium" style="color: #3dc7a4">
          <span class="el-dropdown-link" style="font-size: x-large">
            License<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="detailsRequest"
                >Details</el-dropdown-item
              >
              <el-dropdown-item @click="byTopicRequest"
                >By Topic</el-dropdown-item
              >
              <el-dropdown-item @click="starsRequest">Stars</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </el-col>
      <el-col :span="4">
        <el-dropdown>
          <span class="el-dropdown-link" style="font-size: x-large">
            Info<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="byNameRequest"
                >Search Project By Name</el-dropdown-item
              >
              <el-dropdown-item @click="byTopicRequest"
                >By License</el-dropdown-item
              >
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </el-col>
    </el-row>

    <br />
    <br />
    <br />

    <el-row type="flex" justify="center" align="middle">
      <div v-if="hintVisible" style="font-size: 50px">Choose one query</div>
    </el-row>
    <el-row type="flex" justify="center" align="middle">
      <el-col :span="4"
        ><el-input v-if="inputVisible" v-model="search_name"></el-input
      ></el-col>
      <el-col :span="4"
        ><el-button type="primary" @click="InputConfirmed" v-if="confirmVisible"
          >Confirm</el-button
        ></el-col
      >
    </el-row>
    <br /><br /><br /><br />
    <el-row v-if="hint2Visible" type="flex" justify="center" align="middle"
      ><div style="color: #2e479c">Try Inputting "algorithm-base"</div></el-row
    >

    <br /><br />
    <el-row type="flex" justify="center" align="middle">
      <MyChart
        v-if="chartVisible"
        style="padding-left: 70px"
        :xAxisAttr="xAxis"
        :yAxisVal="yAxis"
      ></MyChart>
      <br /><br />
      <MyTable
        v-if="tableVisible"
        :table="tableData"
        style="padding-left: 70px; background-color: #f8f5ef"
      ></MyTable>
    </el-row>
    <div v-if="projectInfoVisible">
      <p id="project_name"></p>
      <br /><br />
      <p id="project_html_url"></p>
      <br /><br />
      <p id="project_description"></p>
      <br /><br />
      <p id="project_created_time"></p>
      <br /><br />
      <p id="project_license"></p>
      <br /><br />
      <p id="project_stargazers_count"></p>
      <br /><br />
      <p id="project_forks_count"></p>
      <br /><br />
      <p id="project_topics"></p>
    </div>
    <br /><br />
    <br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
  </body>
</template>

<script>
import MyChart from "./components/MyChart.vue";
import MyTable from "./components/MyTable.vue";
export default {
  name: "App",
  components: { MyChart, MyTable },
  data() {
    return {
      hint2Visible: false,
      confirmVisible: false,
      projectInfoVisible: false,
      inputVisible: false,
      hintVisible: true,
      chartVisible: false,
      tableVisible: false,
      showChart: true,
      xAxis: [],
      yAxis: [],
      search_name: "algorithm-base",
      tableData: [],
    };
  },
  methods: {
    async detailsRequest() {
      this.hint2Visible = false;
      this.hintVisible = false;
      this.tableVisible = false;
      this.chartVisible = true;
      this.projectInfoVisible = false;
      this.inputVisible = false;
      this.confirmVisible = false;
      this.axios
        .get("/license/details")
        .then((data) => {
          this.xAxis = [];
          this.yAxis = [];
          let count = 0;
          for (let item in data.data) {
            if (count++ > 5) {
              break;
            }
            this.xAxis.push(data.data[item].name);
            this.yAxis.push(data.data[item].count);
          }
          console.log(data.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    async byTopicRequest() {
      this.hint2Visible = false;
      this.hintVisible = false;
      this.tableVisible = true;
      this.chartVisible = false;
      this.projectInfoVisible = false;
      this.inputVisible = false;
      this.confirmVisible = false;
      this.tableData = [
        {
          license: "gpl",
          1: "spring",
          2: "servlet",
          3: "javafx",
        },
        {
          license: "bsd",
          1: "spring",
          2: "servlet",
          3: "javafx",
        },
        {
          license: "mit",
          1: "spring",
          2: "servlet",
          3: "javafx",
        },
      ];
      // this.axios
      //   .get("/license/by_topic")
      //   .then((data) => {
      //     this.xAxis = [];
      //     this.yAxis = [];
      //     let count = 0;
      //     for (let item in data.data) {
      //       if (count++ > 5) {
      //         break;
      //       }
      //       this.xAxis.push(data.data[item].name);
      //       this.yAxis.push(data.data[item].count);
      //     }
      //     console.log(data.data);
      //   })
      //   .catch((err) => {
      //     console.log(err);
      //   });
    },
    async starsRequest() {
      this.hint2Visible = false;
      this.hintVisible = false;
      this.tableVisible = false;
      this.chartVisible = true;
      this.projectInfoVisible = false;
      this.inputVisible = false;
      this.confirmVisible = false;
      this.axios
        .get("/license/stars")
        .then((data) => {
          this.xAxis = [];
          this.yAxis = [];
          let count = 0;
          for (let item in data.data) {
            if (count++ > 5) {
              break;
            }
            this.xAxis.push(data.data[item].name);
            this.yAxis.push(data.data[item].count);
          }
          console.log(data.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    async byNameRequest() {
      this.hint2Visible = true;
      this.hintVisible = false;
      this.tableVisible = false;
      this.chartVisible = false;
      this.inputVisible = true;
      this.confirmVisible = true;
      this.projectInfoVisible = false;
    },
    async InputConfirmed() {
      this.hint2Visible = false;
      this.inputVisible = true;
      this.confirmVisible = true;
      this.projectInfoVisible = true;
      this.axios
        .get("/info/by_name/" + this.search_name)
        .then((data) => {
          document.getElementById("project_name").innerHTML =
            "name: " + data.data[0].name;
          document.getElementById("project_html_url").innerHTML =
            "html_url: " + data.data[0].html_url;
          document.getElementById("project_description").innerHTML =
            "description: " + data.data[0].description;
          document.getElementById("project_created_time").innerHTML =
            "created_time: " + data.data[0].created_time;
          document.getElementById("project_license").innerHTML =
            "license: " + data.data[0].license;
          document.getElementById("project_stargazers_count").innerHTML =
            "stargazers_count: " + data.data[0].stargazers_count;
          document.getElementById("project_forks_count").innerHTML =
            "forks_count: " + data.data[0].forks_count;
          document.getElementById("project_topics").innerHTML =
            "topics: " + data.data[0].topics;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style>
Page {
  background: #e3cea3;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.el-dropdown-link {
  cursor: pointer;
  color: #409eff;
}
.el-icon-arrow-down {
  font-size: 12px;
}
p {
  font-family: "Times New Roman";
  font-size: 20px;
  color: #2e479c;
}
</style>
