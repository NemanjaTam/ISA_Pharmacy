<script>
import { Pie } from "vue-chartjs";

export default {
  extends: Pie,
  props: {
    label: {
      type: String
    },
    chartData: {
        type:Array/Object/String/Number
     
    },
    dates:{ type:Array/Object/String/Number},
    options: {
      type: Object
    },
    chartColors: {
      type: Object
    }
  },
  mounted() {

       const {
      borderColor,
      pointBorderColor,
      pointBackgroundColor,
      backgroundColor
    } = this.chartColors;

    this.renderChart(
      {
        labels: this.dates,
        datasets: [
          {
            label: this.label,
            data: this.chartData,
            borderColor: borderColor,
            pointBorderColor: pointBorderColor,
            pointBackgroundColor: pointBackgroundColor,
            backgroundColor: backgroundColor
          
          }
        ]
      },
      this.options
    );
  }
};
</script>
