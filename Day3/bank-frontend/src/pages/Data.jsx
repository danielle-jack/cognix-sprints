import { useEffect, useState } from 'react'
import DataService from '../api/DataService'
import Customer from '../models/Customer'
import './Data.css'

export default function Data({ role, currentUser }) {
  const [customers, setCustomers] = useState([])
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)
  
  // New Customer Form State
  const [newUsername, setNewUsername] = useState('')
  const [newPassword, setNewPassword] = useState('')
  const [isCreating, setIsCreating] = useState(false)

  const fetchCustomers = () => {
    setLoading(true)
    DataService.getCustomers()
      .then((res) => {
        const list = Array.isArray(res) ? res.map((c) => Customer.from(c)) : []
        setCustomers(list)
      })
      .catch((err) => {
        setError(err.message || 'Failed to load customers')
      })
      .finally(() => {
        setLoading(false)
      })
  }

  useEffect(() => {
    fetchCustomers()
  }, [])

  const handleCreate = (e) => {
    e.preventDefault()
    if (!newUsername || !newPassword) return

    setIsCreating(true)
    DataService.createData({ username: newUsername, password: newPassword })
      .then(() => {
        setNewUsername('')
        setNewPassword('')
        fetchCustomers() // Refresh the list
      })
      .catch((err) => {
        alert('Failed to create customer: ' + err.message)
      })
      .finally(() => {
        setIsCreating(false)
      })
  }

  return (
    <div className="customers">
      <div className="create-customer-form" style={{ marginBottom: '20px', padding: '10px', border: '1px solid #ccc', borderRadius: '5px' }}>
        <h3>Create New Customer</h3>
        <form onSubmit={handleCreate} style={{ display: 'flex', gap: '10px', alignItems: 'center' }}>
          <input 
            type="text" 
            placeholder="Username" 
            value={newUsername} 
            onChange={(e) => setNewUsername(e.target.value)} 
            required 
          />
          <input 
            type="password" 
            placeholder="Password" 
            value={newPassword} 
            onChange={(e) => setNewPassword(e.target.value)} 
            required 
          />
          <button type="submit" disabled={isCreating}>
            {isCreating ? 'Creating...' : 'Create Customer'}
          </button>
        </form>
      </div>

      {loading && <p>Loading customers...</p>}
      {error && <p style={{ color: 'red' }}>Error: {error}</p>}
      {!loading && !error && customers.length === 0 && <p>No customers found.</p>}

      {!loading && !error && customers.map((c) => (
        <div key={c.id} className="customer-row">
          <div className="customer-avatar">{(c.name || '?').charAt(0).toUpperCase()}</div>
          <div>
            <p className="customer-name">{c.name}</p>
          </div>
          <div className="customer-id">{c.id}</div>
        </div>
      ))}
    </div>
  )
}