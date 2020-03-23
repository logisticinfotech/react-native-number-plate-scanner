<p align="left">
<a href="https://www.npmjs.com/package/@logisticinfotech/react-native-number-plate-scanner"><img alt="npm version" src="https://img.shields.io/badge/npm-v1.0.3-green.svg"></a>
<a href="https://www.npmjs.com/package/@logisticinfotech/react-native-number-plate-scanner"><img src="https://img.shields.io/badge/downloads-%3E1K-yellow.svg"></a>
<a href="https://www.npmjs.com/package/@logisticinfotech/react-native-number-plate-scanner"<><img src="https://img.shields.io/badge/license-MIT-orange.svg"></a>
</p>

# @logisticinfotech/react-native-number-plate-scanner

## Getting started

`$ npm install @logisticinfotech/react-native-number-plate-scanner --save`

### Mostly automatic installation

`$ react-native link @logisticinfotech/react-native-number-plate-scanner`

### Note

Working only with android.

## Usage

```javascript
import { NumberPlateScanner } from "@logisticinfotech/react-native-number-plate-scanner";

// TODO: What to do with the module?

onPressScan = () => {
  NumberPlateScanner().then(result => {
    console.log(result);
  });
};
```
