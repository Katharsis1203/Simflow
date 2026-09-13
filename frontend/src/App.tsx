import {useState} from 'react'

type SimulationResult = {
    resourceName: String
    finalAmount: number
    durationHours: number
}


function App() {
    const [resourceName, setResourceName] = useState('coconuts')
    const [initialAmount, setInitialAmount] = useState(100)
    const [productionPerHour, setProductionPerHour] = useState(5)
    const [consumptionPerHour, setConsumptionPerHour] = useState(8)
    const [durationHours, setDurationHours] = useState(24)
    const [result, setResult] = useState<SimulationResult | null>(null)

    async function handleSubmit(event: React.FormEvent<HTMLFormElement>) {
        event.preventDefault()

        const response = await fetch('http://localhost:8080/api/simulations', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                resourceName,
                initialAmount,
                productionPerHour,
                consumptionPerHour,
                durationHours,
            }),
        })


        setResult(await response.json())
    }


    return (
        <main>
            <h1>SimFlow</h1>
            <p>Run a resource simulation.</p>

            <form onSubmit={handleSubmit}>
                <label>
                    Resource name
                    <input
                        value={resourceName}
                        onChange={(event) => setResourceName(event.target.value)}
                    />
                </label>

                <label>
                    Initial amount
                    <input
                        type="number"
                        value={initialAmount}
                        onChange={(event) => setInitialAmount(Number(event.target.value))}
                    />
                </label>

                <label>
                    Production Per Hour
                    <input
                        type="number"
                        value={productionPerHour}
                        onChange={(event) => setProductionPerHour(Number(event.target.value))}
                    />
                </label>

                <label>
                    Consumption per hour
                    <input
                        type="number"
                        value={consumptionPerHour}
                        onChange={(event) => setConsumptionPerHour(Number(event.target.value))}
                    />
                </label>

                <label>
                    Duration hours
                    <input
                        type="number"
                        value={durationHours}
                        onChange={(event) => setDurationHours(Number(event.target.value))}
                    />
                </label>
                <button type="submit">Run simulation</button>
            </form>

            {result && (
                <div>
                    <h2>Simulation result</h2>
                    <p>Resource: {result.resourceName}</p>
                    <p>Final amount: {result.finalAmount}</p>
                    <p>Duration: {result.durationHours} hours</p>
                </div>
            )}

            <p>Current resource: {resourceName}</p>
        </main>
    )
}

export default App