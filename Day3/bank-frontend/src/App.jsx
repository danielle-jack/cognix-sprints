import { useState } from 'react'
import heroImg from './assets/hero.png'
import './App.css'
import Header from './components/Header'
import Data from './pages/Data'

function App() {
  const [page, setPage] = useState('Home')
  
  // Login States
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const [role, setRole] = useState(null)

  // Strict Login Rules
  const handleLogin = (e) => {
    e.preventDefault()
    if (username === 'admin') {
      setRole('admin')
      setPage('Data')
      alert("Welcome Admin! Routing to Admin Dashboard...")
    } else if (username.trim() !== '') {
      setRole('customer')
      setPage('Data')
      alert(`Welcome ${username}! Routing to Customer Dashboard...`)
    }
  }

  return (
    <div className="app">
      <Header activePage={page} onNavigate={setPage} />

      {page === 'Data' ? (
        <Data role={role} currentUser={username} /> //login to specific account
      ) : page === 'Login' ? (
        <main className="hero">
          <div className="hero-content">
            <h2>Sign In to Acme Bank</h2>
            <form onSubmit={handleLogin} style={{ display: 'flex', flexDirection: 'column', gap: '10px', maxWidth: '300px', margin: '0 auto' }}>
              <input type="text" placeholder="Username" value={username} onChange={(e) => setUsername(e.target.value)} required />
              <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} required />
              <button type="submit" className="primary">Login</button>
            </form>
          </div>
        </main>
      ) : (
        <>
          <main className="hero">
            <div className="hero-content">
              <h2>Banking made really simple</h2>
              <p>Secure accounts, instant transfers, and smart savings tools.</p>
              <div className="cta">
                <button className="primary" onClick={() => setPage('Login')}>Lets Get Started</button>
              </div>
            </div>
            <div className="hero-image">
              <img src={heroImg} alt="Customers using banking app" />
            </div>
          </main>
          
          <section className="features">
            <div className="feature-card">
              <h3>Easy Transfers</h3>
              <p>Move money instantly with low fees and great security.</p>
            </div>
            <div className="feature-card">
              <h3>Smart Savings</h3>
              <p>Automated rules and insights to grow your balance.</p>
            </div>
            <div className="feature-card">
              <h3>24/7 Support</h3>
              <p>Chat and phone support whenever you need help.</p>
            </div>
          </section>

          <footer className="footer">
            <p>© {new Date().getFullYear()} Acme Bank. All rights reserved.</p>
          </footer>
        </>
      )}
    </div>
  )
}

export default App