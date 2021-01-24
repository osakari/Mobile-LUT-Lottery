# `Lottery`

<b>Lottery is an app</b> that generates Eurojackpot and Lotto numbers. The initial view has the <i>Eurojackpot</i> radio button selected, so after pushing the <i>Draw</i> button ten rows of <i>Eurojackpot</i> balls, complete with the <i>Star Balls</i>, are generated.
<p/>
Likewise, after selection of the <i>Lotto</i> radio button, pushing <i>Draw</i> produces six rows of <i>Lotto</i> balls, with <i>Plus</i> balls included.
<p/>
Currently there is no release version of the app. You only can get the whole package and run it with Android Studio.
<p/>
The wievs are as follows:
<p/>

<table>
  <tr>
    <td><b>Eurojackpot</b></td>
    <td><b>Lotto</b></td>
  </tr>
  <tr>
    <td><img src="images/Eurojackpot_View.jpg"/></td>
    <td><img src="images/Lotto_View.jpg"/></td>
  </tr>
</table>
<p/>
<b>The Idea</b>
<p/>
<b>The idea here</b> is to use a random generator for creating rows of balls so that all ball numbers in the range would occur (at least) once. For Eurojackpot, with the range of 1-50, and five balls in a row, that means ten rows of balls. The <i>Star Balls</i> (range 1-10) are drawn separately for each row.
<p/>
For Lotto, with the range of 1-40, and seven balls in a row, we'll need 6 rows, and two numbers in the last row would then appear two times in the resulting rows. A <i>Plus</i> ball in the range of 1-30 are drawn for each row separately.
<p/>
Text views positioned for both games, 10 * (5 + 1 + 2) = 80 for Eurojackpot, and 6 * (7 + 1 + 1) = 54 for Lotto, are made invisible ("gone") initially and when switching from one game to another. Balls of the active game are made visible when populated with the random numbers, and the balls of the other game are made invisible.
<p/>
Getting new numbers can be repeated by pushing the <i>Draw</i> button again.
<p/>
<b>Behind the Scenes</b>
<p/>
<b>For each draw,</b> initially a list with a size of the range of the balls is set so that each list item contains its index. Using the random generator with the same range a number is generated. This number is then used as an index to pop a number from the list.
<p/>
Another number is then generated using the smaller range. That number is used as an index to the now shorter list to pop the next ball, and so on, until all balls have been generated. This method uses only one random number per ball. Had we used a more straightforward method getting random numbers from the initial range, we would have had to discard the numbers that we already have picked. 
<p/>
<b>License</b>
<p/>

```
Copyright 2021 Olavi Sakari. 

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at 

    http://www.apache.org/licenses/LICENSE-2.0 

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

