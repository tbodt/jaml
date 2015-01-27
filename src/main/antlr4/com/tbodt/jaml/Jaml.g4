/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

grammar Jaml;

file: mappings EOF;

mappings: (NL* VALUE '=' value)* NL*;

value: VALUE              # String
     | '{' mappings '}'   # Map
     ;

VALUE: ID | STRING;
ID: [a-zA-Z_.-][a-zA-Z0-9_.-]*;
STRING: '"' ~["]*? '"';

NL: '\r'? '\n';
WS: [ \t\f]+ -> skip;