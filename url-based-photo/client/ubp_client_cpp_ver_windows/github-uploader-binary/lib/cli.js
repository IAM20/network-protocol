#!/usr/bin/env node
'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.parseTextareaValue = undefined;

var _asyncToGenerator2 = require('babel-runtime/helpers/asyncToGenerator');

var _asyncToGenerator3 = _interopRequireDefault(_asyncToGenerator2);

var _slicedToArray2 = require('babel-runtime/helpers/slicedToArray');

var _slicedToArray3 = _interopRequireDefault(_slicedToArray2);

var _minimist = require('minimist');

var _minimist2 = _interopRequireDefault(_minimist);

var _chalk = require('chalk');

var _chalk2 = _interopRequireDefault(_chalk);

var _help = require('./help');

var _help2 = _interopRequireDefault(_help);

var _upload = require('./upload');

var _upload2 = _interopRequireDefault(_upload);

var _package = require('../package.json');

var _package2 = _interopRequireDefault(_package);

var _table = require('./utils/table');

var _table2 = _interopRequireDefault(_table);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

const GENERAL_REGEX = /[!]?\[(.*)\]\((.*)\)/;
const IMGTAG_REGEX = /[^<img].*alt="(.*)" src="(.*)"[>$]/;

const parseTextareaValue = exports.parseTextareaValue = textareaValue => {
  const fileTexts = textareaValue.replace(/\n$/, '').split('\n');

  return fileTexts.map(fileText => {
    const regex = fileText[0] !== '<' ? GENERAL_REGEX : IMGTAG_REGEX;

    var _fileText$match = fileText.match(regex),
        _fileText$match2 = (0, _slicedToArray3.default)(_fileText$match, 3);

    const originalText = _fileText$match2[0],
          name = _fileText$match2[1],
          url = _fileText$match2[2];


    return {
      originalText,
      name,
      url
    };
  });
};

const main = (() => {
  var _ref = (0, _asyncToGenerator3.default)(function* (argv) {
    if (argv.v || argv.version || argv._[0] === 'version') {
      console.log(_package2.default.version);
      process.exit(0);
    }

    if (argv.h || argv.help || argv._[0] === 'help' || argv._.length === 0) {
      (0, _help2.default)();
      process.exit(0);
    }

    if (argv._.length !== 0) {
      try {
        const textareaValue = yield (0, _upload2.default)(argv._);
        const files = parseTextareaValue(textareaValue);

        files.forEach(function (file) {
          process.stdout.write(file.url);});
      } catch (e) {
        console.error(e);
      }
    }

    process.exit(0);
  });

  return function main(_x) {
    return _ref.apply(this, arguments);
  };
})();

main((0, _minimist2.default)(process.argv.slice(2))).catch(console.error);

exports.default = main;
//# sourceMappingURL=cli.js.map
