'use strict';

var _asyncToGenerator2 = require('babel-runtime/helpers/asyncToGenerator');

var _asyncToGenerator3 = _interopRequireDefault(_asyncToGenerator2);

var _chalk = require('chalk');

var _chalk2 = _interopRequireDefault(_chalk);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

module.exports = (0, _asyncToGenerator3.default)(function* () {
  console.log(`
    ${_chalk2.default.green('github-uploader')}

    Upload attachments to GitHub in CLI.
    Get public URL to your files.

    ${_chalk2.default.blue('Usage:')}

       github-uploader [options] [command]

    ${_chalk2.default.blue('Commands:')}

      [file path]    Path of the file to be uploaded

    ${_chalk2.default.blue('Options:')}

      -v, --version  Output the version number
      -h, --help     Output usage information

  `);
});
//# sourceMappingURL=help.js.map