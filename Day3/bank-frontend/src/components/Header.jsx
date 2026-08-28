import './Header.css'

export default function Header({ activePage, onNavigate }) {
  return (
    <header className="header">
      <div className="logo" onClick={() => onNavigate('Home')}>
        THE Bank
      </div>
      <nav className="nav-links">
        {activePage === 'Data' ? (
          <button onClick={() => onNavigate('Home')}>Logout</button>
        ) : (
          <button onClick={() => onNavigate('Login')}>Sign In</button>
        )}
      </nav>
    </header>
  )
}