<h1>Perfect Maze Assignment</h1>
<ol>
<li> Generate a maze like the one below:</li>

<img src = "https://s3.amazonaws.com/ed.edim.co/9307984/perfect_maze.png?response-content-disposition=filename%3D%22perfect%20maze.png%22%3B%20filename%2A%3DUTF-8%27%27perfect%2520maze.png&AWSAccessKeyId=AKIAJXGLFWCDC7HTECXQ&Expires=1486743895&Signature=9C6DrrjKvKZs1UQzvDfKzkK%2FH4E%3D"></img>


<li>Write a program Maze.java that takes a command line parameter N, and generates a random N-by-N perfect maze. A maze is perfect if it has exactly one path between every pair of points in the maze, i.e., no inaccessible locations, no cycles, and no open spaces. Here's a nice algorithm to generate such mazes. Consider an N-by-N grid of cells, each of which initially has a wall between it and its four neighboring cells. For each cell (x, y), maintain a variable north[x][y] that is true if there is wall separating (x, y) and (x, y + 1). We have analogous variables east[x][y], south[x][y], and west[x][y] for the corresponding walls. Note that if there is a wall to the north of (x, y) then north[x][y] = south[x][y+1] = true. Construct the maze by knocking down some of the walls as follows:
  <ol>
  <li> Start at the lower level cell (1, 1).</li>
  
 <li> Find a neighbor at random that you haven't yet been to.</li>
 
 <li> If you find one, move there, knocking down the wall. If you don't find one, go back to the previous cell.</li>

 <li> Repeat steps ii. and iii. until you've been to every cell in the grid.</li></ol></li>
 </ol>
Hint: maintain an (N+2)-by-(N+2) grid of cells to avoid tedious special cases.
