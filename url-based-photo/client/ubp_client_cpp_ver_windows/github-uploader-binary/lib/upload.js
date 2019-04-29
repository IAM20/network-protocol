'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _asyncToGenerator2 = require('babel-runtime/helpers/asyncToGenerator');

var _asyncToGenerator3 = _interopRequireDefault(_asyncToGenerator2);

var _puppeteer = require('puppeteer');

var _puppeteer2 = _interopRequireDefault(_puppeteer);

var _path = require('path');

var _path2 = _interopRequireDefault(_path);

var _ora = require('ora');

var _ora2 = _interopRequireDefault(_ora);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

const getAbsoluteFulePaths = filePaths => {
  const cwd = process.cwd();
  return filePaths.map(filePath => _path2.default.resolve(cwd, filePath));
};

const upload = (() => {
  var _ref = (0, _asyncToGenerator3.default)(function* (filePaths) {
    //const spinner = (0, _ora2.default)('Start to login...').start();

    const absoluteFilePaths = getAbsoluteFulePaths(filePaths);

    const browser = yield _puppeteer2.default.launch();
    const page = yield browser.newPage();

    yield page.goto('https://github.com/login');
    yield page.type('#login_field', 'xxxhomey19');
    yield page.type('#password', 'abc12345'); // Please DO NOT change this password!!!!!!!
    yield page.click('input[type="submit"]');

    try {
      yield page.waitForSelector('body.logged-in', { timeout: 15000 });

      //spinner.succeed('Login Successfully.');
      //spinner.start('Start to upload files...');
    } catch (e) {
      //spinner.fail('Login failed.');

      process.exit(0);
    }

    yield page.goto('https://github.com/xxhomey19/github-uploader/issues/new');

    const uploadElementHandle = yield page.$('.js-manual-file-chooser');

    yield uploadElementHandle.uploadFile(...absoluteFilePaths);
    yield page.waitFor(1000);

    try {
      yield page.waitForSelector('file-attachment.js-upload-markdown-image.is-default', { timeout: 15000 });

      const textareaValue = yield page.$eval('textarea#issue_body', function (el) {
        return el.value;
      });

      yield page.close();

      //spinner.succeed('Upload Successfully.');
      return textareaValue;
    } catch (e) {
      //spinner.fail('Upload files failed.');

      process.exit(0);
    }
  });

  return function upload(_x) {
    return _ref.apply(this, arguments);
  };
})();

exports.default = upload;
//# sourceMappingURL=upload.js.map
