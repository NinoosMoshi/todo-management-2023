import { NavLink } from "react-router-dom";
import logo from "../assets/images/logo.png";

const HeaderComponent = () => {
  return (
    <div>
      <header>
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
          <a className="navbar-brand mx-lg-5" href="#">
            <img className="logo" src={logo} height="25" />
            <span className="ml-3">TODO</span>
          </a>
          <button
            className="navbar-toggler"
            type="button"
            data-toggle="collapse"
            data-target="#navbar1"
            aria-controls="navbarNav"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbar1">
            <ul className="navbar-nav ml-auto">
              <li className="nav-item">
                <NavLink to="/todos" className="nav-link">
                  Todos
                </NavLink>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="#">
                  Home
                </a>
              </li>
              <li className="nav-item dropdown position-absolute top-1 end-0 mx-lg-5">
                <a
                  className="nav-link  dropdown-toggle"
                  href="#"
                  data-toggle="dropdown"
                >
                  Profile
                </a>
                <ul className="dropdown-menu">
                  <li className="nav-item">
                    <NavLink to="/login" className="dropdown-item">
                      Login
                    </NavLink>
                  </li>
                  <li className="nav-item">
                    <NavLink to="/register" className="dropdown-item">
                      Register
                    </NavLink>
                  </li>
                </ul>
              </li>
            </ul>
          </div>
        </nav>
      </header>
    </div>
  );
};

export default HeaderComponent;
