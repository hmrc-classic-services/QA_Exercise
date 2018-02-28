#!/usr/bin/env bash

cd QA_App
sbt test
sbt "run 8090"

