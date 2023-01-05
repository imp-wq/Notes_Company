<template>
  <VChart class="echarts" :option="option" v-bind="$attrs" />
</template>

<script lang="ts" setup name="LineWidget2">
// @ts-nocheck
import { computed } from 'vue'
import VChart from 'vue-echarts'

const props = defineProps({
  data: {
    type: Object,
    default: () => {
      return []
    }
  },
  map: {
    type: Object,
    default: () => {
      return {
        line1: '出生人数',
        line2: '死亡人数'
      }
    }
  },
  filter: {
    type: Object || Function,
    default: () => {
      return {
        label: 'label',
        value: 'value',
        series: 'series'
      }
    }
  },
  colors: {
    type: Array,
    default: () => {
      return ['#00E0FF', '#F6B52E', '#FF8732']
    }
  },
  xAxisLabel: {
    type: Object,
    default: () => {
      return {}
    }
  },
  yAxisLabel: {
    type: Object,
    default: () => {
      return {}
    }
  },
  yAxis: {
    type: Object,
    default: () => {
      return {}
    }
  },
  tooltip: {
    type: Object,
    default: () => {
      return {}
    }
  },
  legend: {
    type: Object,
    default: () => {
      return {}
    }
  },
  grid: {
    type: Object,
    default: () => {
      return {}
    }
  }
})

const filterData = computed(() => {
  let list = [...props.data]
  if (typeof props.filter == 'object') {
    list = list.map((o) => {
      const obj = Object.assign({}, o)
      Object.keys(props.filter).map((key) => {
        if (key != props.filter[key]) {
          obj[key] = obj[props.filter[key]]
          delete obj[props.filter[key]]
        }
      })
      return obj
    })
  } else if (typeof props.filter == 'function') {
    list = props.filter(list)
  }
  return list
})

// 数值转“万”为单位
const num2wan = (value) => {
  if (value >= 10000) {
    const num = value / 10000
    if (String(num).indexOf('.') >= 0) {
      return num.toFixed(2) + '万'
    } else {
      return num + '万'
    }
  } else {
    return value
  }
}

const option = computed(() => {
  // const xAxisData = ['1时', '2时', '3时', '4时', '5时', '6时'] // 类目轴数据
  // const sampleSpeedName = ['出生人数', '死亡人数'] // 图例label
  // // 折线图数据，配置多条折线
  // const sampleSpeedData = [
  //   [100000, 150000, 200000, 150000, 100000, 120000],
  //   [120000, 200000, 180000, 160000, 180000, 100000]
  // ]
  // const colors = ['#1595D3', '#00FC74', '#FEFD58', '#F58B3B', '#A46EFF'] // 折线图配色方案，一条折线对应一个颜色

  const sampleSpeedName = [...new Set(filterData.value.map((o) => o.series))]
  console.log({ sampleSpeedName })

  const xAxisData = filterData.value.filter((o) => o.series == sampleSpeedName[0]).map((o) => o.label)
  const sampleSpeedData = [
    ...sampleSpeedName.map((name) => filterData.value.filter((o) => o.series == name).map((o) => o.value))
  ]
  console.log({ sampleSpeedData })

  const colors = props.colors // 折线图配色方案，一条折线对应一个颜色
  return {
    legend: {
      right: 20,
      top: 20,
      data: sampleSpeedName,
      textStyle: {
        color: '#fff'
      },
      ...props.legend
    },
    tooltip: {
      show: true,
      trigger: 'axis',
      axisPointer: {
        lineStyle: {
          color: '#3394D3'
        }
      },
      textStyle: {
        color: '#fff'
      },
      backgroundColor: 'rgba(1, 48, 81, 0.9)',
      borderColor: '#23B9FF',
      formatter: function (params) {
        return [
          `<div style="display: inline-block; height: 20px; line-height: 20px">${params[0].axisValue}</div>`,
          ...params.map((p) => {
            return [
              '<span style="display: inline-block; height: 12px; width: 12px; margin-right: 10px; background-color: ',
              p.color,
              '; border-radius: 50%"></span>',
              '<span style="display: inline-block; width: 120px">',
              p.seriesName,
              '</span>',
              '<span style="font-weight: bold; color: ',
              p.color,
              '">',
              p.value,
              '</span>'
            ].join('')
          })
        ].join('<br>')
      },
      ...props.tooltip
    },
    grid: {
      top: 80,
      bottom: 50,
      left: 60,
      right: 20,
      ...props.grid
    },
    xAxis: [
      {
        type: 'category',
        boundaryGap: false,
        data: xAxisData,
        axisLabel: {
          // interval: 0,
          color: '#fff',
          padding: [10, 0, 0, 0],
          ...props.xAxisLabel
        },
        axisLine: {
          onZero: false,
          lineStyle: {
            color: 'rgba(221, 242, 255, 0.2)'
          }
        },
        splitLine: {
          show: false
        }
      }
    ],
    yAxis: {
      type: 'value',
      offset: 10,
      axisLabel: {
        color: '#D1E4FF',
        // 自定义Y轴数据标签
        formatter: function (value) {
          return num2wan(value)
        },
        ...props.yAxisLabel
      },
      splitLine: {
        show: true,
        lineStyle: {
          color: ['rgba(168, 215, 255, 0.3)']
        }
      },
      ...props.yAxis
    },
    series: [
      ...sampleSpeedName.map((o, i) => {
        return {
          name: o,
          data: sampleSpeedData[i],
          type: 'line',
          smooth: true,
          itemStyle: {
            color: colors[i]
          },
          lineStyle: {
            color: colors[i]
          }
        }
      })
    ]
  }
})
</script>

<style lang="scss" scoped>
.echarts {
  display: inline-block;
  box-sizing: border-box;
  height: 100%;
  width: 100%;
}
</style>
