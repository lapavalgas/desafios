import React from 'react';
import './style.css';


// bridgeUser

const Signin = (props) => {
    return (

        <div class="signup">
            <h1>Sign in</h1>
            <form class="signupForm" action="signin" action="signup" method="POST">
                <span class="signupName"><input class="headerInput" type="text" name="login" id="signupLogin" placeholder="Your user name..." /></span>
                <span class="signupLogin"><input class="headerInput" type="text" name="pass" id="signupPass" placeholder="Your password..." /></span>
                <span class="signupPass"><input class="signupSubmit" type="submit" value="SIGN IN" /></span>
            </form>
        </div>

    )
}

export default Signin;