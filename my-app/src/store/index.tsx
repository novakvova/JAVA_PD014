import { applyMiddleware, combineReducers, createStore } from "redux";
import { composeWithDevTools } from "redux-devtools-extension";
import thunk from "redux-thunk";
import { AuthReducer } from "../components/auth/authReducer";
import { IsLoadingReducer } from "./reducers/isLoadingReducer";


export const rootReducer = combineReducers({
    auth: AuthReducer,
    IsLoadingReducer,
}); 

export const store = createStore(rootReducer,
    composeWithDevTools(applyMiddleware(thunk)));