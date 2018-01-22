if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'Tetris'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'Tetris'.");
}
var Tetris = function (_, Kotlin) {
  'use strict';
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  function main(args) {
    println('Hello, world');
  }
  _.main_kand9s$ = main;
  main([]);
  Kotlin.defineModule('Tetris', _);
  return _;
}(typeof Tetris === 'undefined' ? {} : Tetris, kotlin);
