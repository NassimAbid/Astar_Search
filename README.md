## Programming with A* Search

You will write a program that uses A* search to find a solution to the scenario described below.

You may work in whatever programming language you are comfortable with and will be assessed on the
quality and correctness of your program, and your understanding of the solution.

## Problem description

A robot must load and unload packages into and out of a delivery truck. Packages unloaded from the
delivery truck must be brought to the appropriate warehouse. Small packages must go to Warehouse A and
large packages must go to Warehouse B. Medium packages, which are initially stored in either Warehouse A
or Warehouse B, must be loaded onto the delivery truck. The truck initially contains S small packages and L
large packages. Warehouse A initially contains Ma medium packages and Warehouse B initially contains Mb
medium packages. Assume that both warehouses are adjacent to the truck and that the robot can freely
move between the truck and the warehouses. The robot can only move one package at a time, regardless of
size, from one location to another.

## What to implement

Write a program that uses A* search to solve the above problem. You should design and implement an
appropriate state space, transition function, heuristics, and goal encoding that models the problem
description. The design of the state space is particularly important for this problem. As part of your
program, you should implement two different heuristic functions to be used with A* search. You should also
be prepared to explain the choice and operation of these heuristics. Ensure that your implementation will
work with a number of different problem configurations of S, L, Ma, and Mb. You should test your
implementation with small numbers of packages to ensure it is working correctly before testing it on larger
numbers of packages.
