import { Outlet, Link, useLocation } from 'react-router-dom'

function Layout() {
  const location = useLocation()
  
  const isActive = (path: string) => {
    return location.pathname === path ? 'nav-link active' : 'nav-link'
  }

  return (
    <div className="app-container">
      <nav className="navigation">
        <div className="nav-content">
          <h1 className="app-title">LeafLogic</h1>
          <div className="nav-links">
            <Link to="/" className={isActive('/')}>Home</Link>
            <Link to="/dashboard" className={isActive('/dashboard')}>Dashboard</Link>
            <Link to="/chat" className={isActive('/chat')}>Chat</Link>
            <Link to="/profile" className={isActive('/profile')}>Profile</Link>
          </div>
        </div>
      </nav>
      <main className="main-content">
        <Outlet />
      </main>
    </div>
  )
}

export default Layout
