
import {superWowify, mehify as meh} from './wowify';

const things = ['one', 'two', 'three'];

const result = superWowify(...things, 'four');

console.log(result);

$('body').html(result);