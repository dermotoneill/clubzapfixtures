#!/bin/bash
spreadsheet=$1
page=$2
row=$3
java -jar target/clubzapfixtures-1.0.jar "$spreadsheet" $page $row
