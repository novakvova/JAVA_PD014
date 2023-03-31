import { FcGoogle } from "react-icons/fc";

import loginImg from "../../../assets/login.jpg";
import { Link, useNavigate } from "react-router-dom";
import { AuthUserActionType, IAuthResponse, ILoginPage, IUser } from "../types";
import * as yup from "yup";
import { useFormik } from "formik";
import { useGoogleReCaptcha } from "react-google-recaptcha-v3";
import { APP_ENV } from "../../../env";
import axios from "axios";
import jwtDecode from "jwt-decode";
import { useDispatch } from "react-redux";
import http from "../../../http_common";

const LoginPage = () => {

  const { executeRecaptcha } = useGoogleReCaptcha();

  const initValues: ILoginPage = {
    email: "",
    password: "",
    reCaptchaToken: ""
  };

  const loginSchema = yup.object({
    email: yup.string().required("Поле не повинне бути пустим"),
    password: yup.string().required("Поле не повинне бути пустим"),
  });

  const dispatch = useDispatch();
  const navigator = useNavigate();

  const onSubmitFormik = async (values: ILoginPage) => {
    try {
      if(!executeRecaptcha)
        return;

      values.reCaptchaToken=await executeRecaptcha();  
      const resp = await http.post<IAuthResponse>(`account/login`,values);
      const {token} = resp.data;
      const user = jwtDecode(token) as IUser;
      
      dispatch({
        type: AuthUserActionType.LOGIN_USER,
        payload: user 
      });

      http.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      localStorage.token = token;
      navigator("/");
      //console.log("Login user token ", resp);
    }
    catch(error: any) {
      console.log("Error login user", error);
    }
    console.log("Login form: ", values);
  };

  const formik = useFormik({
    initialValues: initValues,
    onSubmit: onSubmitFormik,
    validationSchema: loginSchema,
   // validateOnChange: true,
    
  });

  const { values, errors, touched, handleSubmit, handleChange, setFieldValue } =
    formik;


  return (
    <>
      <div className="relative w-full h-screen bg-zinc-900/90">
        <img
          className="absolute w-full h-full object-cover mix-blend-overlay"
          src={loginImg}
          alt="/"
        />

        <div className="flex justify-center py-10 ">
          <form className="max-w-[400px] w-full mx-auto bg-white p-8" onSubmit={handleSubmit}>
            <h2 className="text-4xl font-bold text-center py-2">
              Вхід на сайт
            </h2>
            <div className="flex flex-col mb-4">
              <label>Username</label>
              <input
                className="border relative bg-gray-100 p-2"
                type="text"
                name="email"
                id="email"
                onChange={handleChange}
                value={values.email}
              />
              {(errors.email && touched.email) && (
                <p className="mt-2 text-sm text-red-600 dark:text-red-500">
                  <span className="font-medium">{errors.email}</span>
                </p>
              )}
            </div>
            <div className="flex flex-col ">
              <label>Password</label>
              <input
                className="border relative bg-gray-100 p-2"
                type="password"
                name="password"
                id="password"
                onChange={handleChange}
                value={values.password}
              />
              {(errors.password && touched.password) && (
                <p className="mt-2 text-sm text-red-600 dark:text-red-500">
                  <span className="font-medium">{errors.password}</span>
                </p>
              )}
            </div>
            <button className="w-full py-3 mt-8 bg-indigo-600 hover:bg-indigo-500 relative text-white">
              Sign In
            </button>
            <p className="flex items-center mt-2">
              <input className="mr-2" type="checkbox" />
              Remember Me
            </p>
            <Link to="/" className="relative">
              <p className="text-center mt-8">Not a member? Sign up now</p>
            </Link>
            <div className="flex justify-between py-8">
              <p className="border shadow-lg hover:shadow-xl px-6 py-2 relative flex items-center">
                <FcGoogle className="mr-2" /> Google
              </p>
            </div>
          </form>
        </div>
      </div>
    </>
  );
};

export default LoginPage;
