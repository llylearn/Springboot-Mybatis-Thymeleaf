First web demo use spring booot, mybatis and thymeleaf

This application implemented several elemental functionalities
1. User login: The account are inserted into data base, there is no interface for user to sign up.
2. User logout: After login, user can click the logout button on the right-top screen.
3. User add comment: After login, user can submit new comment by using the second block in the main.html
4.User delete comment: After login, user can delete any comment that belong to himself/herself by using left side of the third block in the main.html.
5. User modify password: After login, user can change password by typing old password and new password into right side of the third block in main.html

issues:
1. There is no limitation to check the length of the password and space is not allowed
2. When user submit new comment, there should be a funtionality to remove useless space or empty line
...
