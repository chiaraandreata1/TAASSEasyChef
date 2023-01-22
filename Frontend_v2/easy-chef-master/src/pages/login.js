import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useAuthContext } from "../utils/auth_context";
import { LoginSocialGoogle } from "reactjs-social-login"
import { GoogleLoginButton } from "react-social-login-buttons"
import {createChef, getChefById, getChefByMail} from "../utils/endpoints";

export const Login = () => {

    const navigate = useNavigate();
    const { user, setUser, isLogged } = useAuthContext();
    const google_data = require('../credential-google.json');

    useEffect(() => {
        if (isLogged())
            navigate("/home");
    }, []);

    const login = () => {
        // async function createData() {
        //     const chef = await getChefByMail(document.getElementById("email").value);
        //     console.log(chef);
        //     if(chef) {
        //         setUser({
        //             id: chef.id,
        //             name: chef?.username,
        //             email: chef?.mail
        //         });
        //         // isLogged messo a true
        //         // navigate("/home");
        //     }
        //     // else{
        //     //     // alert failure to add
        //     // }
        // }
        // createData();
        setUser({
            id: 500,
            name: "Mario Rossi",
            email: "mario.rossi@prova.com"
        });
    }

    function handleCallbackResponse({provider,data}){
    //const handleCallbackResponse = async ({provider,data}) => {
        async function createData(data){
            let chef = await getChefByMail(data.email);
            console.log(chef);
            if(chef.length<=0){
                chef = await createChef({"username":data.name,"mail":data.email});
                console.log("created user");
            }
            chef = await getChefByMail(data.email);
            console.log(chef);
            setUser({
                id: chef[0].id,
                name: data.name,
                email: data.email
             });
            // navigate("/home");
        }
        createData(data);
    }
    return (
        <main className="page">
            <h1>{user?.name}</h1>
            <div className="login">
                <h3>Login</h3>
                <form>
                    <LoginSocialGoogle
                        client_id={google_data.web.client_id}
                        scope="openid profile email"
                        discoveryDocs="claim_supported"
                        access_type="offline"
                        onReject={(err) => {
                            console.log(err);
                        }}
                        onResolve={handleCallbackResponse}
                    >
                        <GoogleLoginButton/>
                    </LoginSocialGoogle>
                </form>
            </div>
        </main>
    );
}

// install reactjs-social-login, react-social-login-buttons