<template>
  <body style="background-color: #f8f5ef">
    <br /><br /><br /><br /><br /><br /><br /><br />
    <el-row type="flex" justify="center" align="middle"><h style="font-size: 65px; ">License Analysis</h></el-row>
    <br><br><br>
    <el-row type="flex" justify="center" align="middle">
      <el-col :span="4">
        <el-dropdown size="medium" style="color: #3dc7a4">
          <span class="el-dropdown-link" style="font-size: x-large">
            License<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="CompareRequest"
                >Compare</el-dropdown-item
              >
              <el-dropdown-item @click="detailsRequest"
                >Projects Count</el-dropdown-item
              >
              <el-dropdown-item @click="starsRequest">Stars</el-dropdown-item>
              <el-dropdown-item @click="forksRequest">Forks</el-dropdown-item>
              <el-dropdown-item @click="byTopicRequest"
                >Top3 Topics</el-dropdown-item
              >
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </el-col>
      <el-col :span="4">
        <el-dropdown>
          <span class="el-dropdown-link" style="font-size: x-large">
            Project Info<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="byNameRequest"
                >Search Project By Name</el-dropdown-item
              >
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </el-col>
    </el-row>

    <br />
    <br />
    <br />

    <!-- <el-row type="flex" justify="center" align="middle">
      <div v-if="hintVisible" style="font-size: 50px">Choose one query</div>
    </el-row> -->
    <el-row type="flex" justify="center" align="middle">
      <el-col :span="4">
        <el-select
          clearable
          filterable
          v-if="inputVisible"
          v-model="search_name"
          placeholder="input"
          size="large"
          @clear="inputClear"
          @input="inputChange"
          ><el-option
            v-for="(item, index) in options"
            :key="index"
            :label="item.label"
            :value="item.value" /></el-select
      ></el-col>
      <el-col :span="4"
        ><el-button type="primary" @click="InputConfirmed" v-if="confirmVisible"
          >Confirm</el-button
        ></el-col
      >
    </el-row>
    <br /><br /><br /><br />
    <el-row v-if="hint2Visible" type="flex" justify="center" align="middle"
      ><div style="color: #2e479c">Try Inputting something</div></el-row
    >

    <el-row type="flex" justify="center" align="middle">
      <MyChart
        v-if="chartVisible"
        style="padding-left: 70px"
        :xAxisAttr="xAxis"
        :yAxisVal="yAxis"
        :yAxisLabel="yAxisLabel0"
      ></MyChart>
      <br /><br />
      <MyTable
        v-if="tableVisible"
        :table="tableData"
        style="padding-left: 70px"
      ></MyTable>
      <CompareTable
        v-if="compareTableVisible"
        :table="compareTableData"
        style="padding-left: 70px"
      >
      </CompareTable>
    </el-row>
    <div style="font-weight: bold;" v-if="projectInfoVisible">
      <p  id="project_name"></p>
      <br /><br />
      <a href="javascript:void(0)" id="project_html_url" @click="htmlClick"></a>
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
import CompareTable from "./components/CompareTable.vue";
export default {
  name: "App",
  components: { MyChart, MyTable, CompareTable },
  data() {
    return {
      compareTableVisible: false,
      hint2Visible: false,
      confirmVisible: false,
      projectInfoVisible: false,
      inputVisible: false,
      hintVisible: true,
      chartVisible: false,
      tableVisible: false,
      listVisible: false,
      xAxis: [],
      yAxis: [],
      search_name: "",
      tableData: [],
      compareTableData: [],
      yAxisLabel0: "",
      options: [],
      license_list: [],
      jump_html: "",
    };
  },
  methods: {
    async detailsRequest() {
      this.compareTableVisible = false;
      this.listVisible = false;
      this.hint2Visible = false;
      this.hintVisible = false;
      this.tableVisible = false;
      this.chartVisible = true;
      this.projectInfoVisible = false;
      this.inputVisible = false;
      this.confirmVisible = false;
      this.yAxisLabel0 = "total number";
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
          // console.log(data.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    async byTopicRequest() {
      this.compareTableVisible = false;
      this.listVisible = false;
      this.hint2Visible = false;
      this.hintVisible = false;
      this.tableVisible = true;
      this.chartVisible = false;
      this.projectInfoVisible = false;
      this.inputVisible = false;
      this.confirmVisible = false;

      this.axios
        .get("/license/by_topic")
        .then((data) => {
          this.xAxis = [];
          this.yAxis = [];
          // console.log(data.data);
          for (let item in data.data) {
            let obj = {
              license: data.data[item].license,
              1: "",
              2: "",
              3: "",
            };
            let count = 1;
            for (let topic in data.data[item].topics) {
              obj[count] = data.data[item].topics[topic];
              count++;
            }
            this.tableData.push(obj);
          }
          // console.log(data.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    async starsRequest() {
      this.compareTableVisible = false;
      this.listVisible = false;
      this.hint2Visible = false;
      this.hintVisible = false;
      this.tableVisible = false;
      this.chartVisible = true;
      this.projectInfoVisible = false;
      this.inputVisible = false;
      this.confirmVisible = false;
      this.yAxisLabel0 = "stars";
      // console.log("here" + this.yAxisLabel0);
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
          // console.log(data.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    async forksRequest() {
      this.compareTableVisible = false;
      this.listVisible = false;
      this.hint2Visible = false;
      this.hintVisible = false;
      this.tableVisible = false;
      this.chartVisible = true;
      this.projectInfoVisible = false;
      this.inputVisible = false;
      this.confirmVisible = false;
      this.yAxisLabel0 = "forks";
      console.log("here" + this.yAxisLabel0);
      this.axios
        .get("/license/forks")
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
          // console.log(data.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    async byNameRequest() {
      this.compareTableVisible = false;
      this.listVisible = false;
      this.hint2Visible = true;
      this.hintVisible = false;
      this.tableVisible = false;
      this.chartVisible = false;
      this.inputVisible = true;
      this.confirmVisible = true;
      this.projectInfoVisible = false;
    },
    async InputConfirmed() {
      this.compareTableVisible = false;
      this.listVisible = false;
      this.hint2Visible = false;
      this.inputVisible = true;
      this.confirmVisible = true;
      this.projectInfoVisible = true;
      this.axios
        .get("/info/get/" + this.search_name)
        .then((data) => {
          // console.log(data);
          document.getElementById("project_name").innerHTML =
            "name: " + data.data[0].name;
          this.jump_html = data.data[0].html_url;
          document.getElementById("project_html_url").innerText =
            data.data[0].html_url;
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
    async byLicenseRequest() {
      this.compareTableVisible = false;
      if (this.license_list.length == 0) {
        this.axios
          .get("/license/stars")
          .then((data) => {
            let count = 0;
            for (let item in data.data) {
              if (count++ > 10) {
                break;
              }
              this.license_list.push(data.data[item].name);
            }
          })
          .catch((err) => {
            console.log(err);
          });
      }
      this.hint2Visible = false;
      this.hintVisible = false;
      this.tableVisible = false;
      this.chartVisible = false;
      this.inputVisible = false;
      this.confirmVisible = false;
      this.projectInfoVisible = false;
      this.listVisible = true;
    },
    async CompareRequest() {
      this.listVisible = false;
      this.hint2Visible = false;
      this.hintVisible = false;
      this.tableVisible = false;
      this.chartVisible = false;
      this.projectInfoVisible = false;
      this.inputVisible = false;
      this.confirmVisible = false;
      this.compareTableVisible = true;
      this.compareTableData = [];
      this.axios
        .get("/license/compare")
        .then((data) => {
          let obj = {
            term: "number",
            licensed: data.data[0],
            unlicensed: data.data[1],
          };
          this.compareTableData.push(obj);
        })
        .catch((err) => {
          console.log(err);
        });
      this.axios
        .get("/license/stars/compare")
        .then((data) => {
          let obj = {
            term: "stars",
            licensed: data.data[0],
            unlicensed: data.data[1],
          };
          this.compareTableData.push(obj);
        })
        .catch((err) => {
          console.log(err);
        });
      this.axios
        .get("/license/forks/compare")
        .then((data) => {
          let obj = {
            term: "forks",
            licensed: data.data[0],
            unlicensed: data.data[1],
          };
          this.compareTableData.push(obj);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    htmlClick() {
      location.href = this.jump_html;
    },
    inputClear() {
      this.search_name = "";
      this.options = [];
      this.$forceUpdate();
    },
    inputChange(val) {
      console.log("sf");
      this.options = [];
      if (val.data == null) {
        this.search_name = this.search_name.slice(
          0,
          this.search_name.length - 1
        );
      } else {
        this.search_name += val.data;
      }
      // console.log(this.search_name);
      this.axios
        .get("/info/search/" + this.search_name)
        .then((data) => {
          console.log(data);
          let count = 0;
          for (let item in data.data) {
            this.options.push({
              value: data.data[item],
              label: data.data[item],
            });
            if (++count > 10) {
              break;
            }
          }
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
  background: #f2e7d0;
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
