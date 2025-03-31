import {SensorMailFunctionReport} from "./sensor-mail-function-report.models";
import {AverageTemperaturePerHourReport} from "./average-temperature-per-hour-report.models";

export function generateRandomSensorHistoryData(count: number): SensorMailFunctionReport[] {
  const sensorData: SensorMailFunctionReport[] = [];

  for (let i = 0; i < count; i++) {
    const randomTemperature = Math.random() * 100; // Random temperature between 0 and 100

    sensorData.push({
      sensorId: `sensor-${i}`,
      averageTemperature: parseFloat(randomTemperature.toFixed(2)), // Limit to 2 decimal places
    });
  }

  return sensorData;
}

export function generateRandomAverageTemperaturePerHourReportData(count: number): AverageTemperaturePerHourReport[] {
  const sensorData: AverageTemperaturePerHourReport[] = [];

  for (let i = 0; i < count; i++) {
    const randomTemperature = Math.random() * 100; // Random temperature between 0 and 100


    sensorData.push({
      processingHour: i * 5,
      averageTemperature: parseFloat(randomTemperature.toFixed(2)), // Limit to 2 decimal places
    });
  }

  return sensorData;
}

// Generate an array of 20 sensor history items
const sensorHistoryArray = generateRandomSensorHistoryData(20);


