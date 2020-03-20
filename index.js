// const { NumberPlateScanner } = "./src/component/NumberPlateScanner.js";

// export default NumberPlateScanner;

import { NativeEventEmitter, NativeModules } from "react-native";

export const NumberPlateScanner = () => {
  return new Promise(async (resolve, reject) => {
    const eventEmitter = new NativeEventEmitter(
      NativeModules.NumberPlateScanner
    );
    eventEmitter.addListener("NumberPlateScannerResult", event => {
      resolve(event);
      eventEmitter.removeListener("NumberPlateScannerResult");
    });
    NativeModules.NumberPlateScanner.scanNumberPlate();
  });
};

export default { NumberPlateScanner };
