import React from 'react';
import './style.css';

const FormSignup = (props) => {
    return (

        <div className="signup">
            <h1>Create an Account</h1>
            <form className="signupForm" action="signup" method="POST">
                <span className="signupTitle"><input className="headerInput" type="text" name="name" id="signupName" placeholder="Your name..." /></span>
                <span className="signupName"><input className="headerInput" type="text" name="login" id="signupLogin" placeholder="Set an user name to login..." /></span>
                <span className="signupLogin"><input className="headerInput" type="text" name="pass" id="signupPass" placeholder="Set an password..." /></span>
                <span className="signupPass"><input className="signupSubmit" type="submit" value="CREATE ACCOUNT" /></span>
                <span className="signupNow">Already have an Account? <a class="link" href="/signin"><span>Sign in now!</span></a></span>
            </form>
        </div>

    )
}

export default FormSignup;