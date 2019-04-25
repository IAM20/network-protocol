'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _cliTable = require('cli-table2');

var _cliTable2 = _interopRequireDefault(_cliTable);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

const table = new _cliTable2.default({
  head: ['Name', 'URL'],
  chars: {
    top: '═',
    'top-mid': '╤',
    'top-left': '╔',
    'top-right': '╗',
    bottom: '═',
    'bottom-mid': '╧',
    'bottom-left': '╚',
    'bottom-right': '╝',
    left: '║',
    'left-mid': '╟',
    mid: '─',
    'mid-mid': '┼',
    right: '║',
    'right-mid': '╢',
    middle: '│'
  }
});

exports.default = table;
//# sourceMappingURL=table.js.map