#D2E Feedback


###u6609958 Xinyao
- None of your methods have Java Docs or adequate comments. 
- VerifyPlacementTest only tests a few cases. You want to test as many different cases as feasible, and in this case you
can iterate through different integers to generate different overlapping test cases for each location on the board. You 
also have some duplicate conditionals in your test - see lines 45 - 47 in VerifyPlacementTest.
- I can't mark commented out code. That is code that doesn't compile because you've written it as comments. (Draggable Class
lines 21-101).
- I would like to see some use of objects rather than just iterating over strings. I can see your group has put a lot of 
effort into the helper methods you have created, but we are looking for you to use Object Oriented features in these methods,
not simply strings stored in different data structures.


###u6818746 Ruiqiao
- Nice work on the second menu, but be careful to make sure that it is easy to read all text on the screen. Because your 
background has different colours in it, the bottom few labels get lost in the background. Enlarging the font size would
help with this too.
- Have you considered how a user will play your game? I shouldn't be using the `makeplacement` textbox in the Viewer. 
- Unfortunately, you haven't submitted a jUnit test, which was one of the requirements for this deliverable, so I am unable 
to award you any marks.


###u6976740 Xinyi (Yvonne) 
- In the future, make sure that your line numbers are included at the time of the deadline.
- Why did you delete the `Menu` class and replace it with the `Menu1` class, simply copy-pasting the code from Menu to Menu1?
- I would have liked to see you write tests for non-JavaFX code. Your test doesn't do much besides launch your application.
As such, I can't justify giving you higher marks.
- Your menus look very well structured, however be sure to give yourself plenty of time to integrate them before D2G.


