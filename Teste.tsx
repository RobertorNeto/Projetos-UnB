/* teste para fetch e hooks react */
"use client";
import { use, useEffect, useState } from "react";
import { preconnect } from "react-dom";

interface Item {
    id: number;
    title: string;
    price: number;
    description: string;
}


export default function Teste() {
    const [showItems, setShowItems] = useState(false);
    const [items, setItems] = useState<Item[]>([]);
    const [index, setIndex] = useState(0);

    const handleNext = () => {
        setIndex(prevIndex => (prevIndex + 1) % items.length)
    }

    const handlePrevious = () => {
        setIndex((prevIndex) => (prevIndex - 1 + items.length) % items.length);
    }

    useEffect(() => {
        fetch("https://dummyjson.com/products").then((response) => {
            response.json().then((data) => {
                setItems(data.products);
                console.log("Puxado");
            })
        }).catch((error) => {
                console.error("Erro ao converter JSON:", error);
        })
}, [showItems]);

  return( 
  <main className="h-screen w-screen flex flex-col items-center">
    <div className="flex flex-col items-center justify-center gap-2 mt-10"> 
        <h1 className="font-bold text-3xl"> Lojinha aleatória </h1>
        <h2 className="font-medium text-xl">Seja Bem-Vindo</h2>
        <p className="font-medium text-lg">Navegue pelos itens abaixo!</p>
    </div>
    <section className="mt-10 flex flex-col items-center">
        <button onClick ={() => setShowItems(!showItems)}className=" bg-indigo-600 text-white p-2 rounded-lg hover:scale-110 duration-200 ">
          Mostrar Itens
        </button>
        {showItems 
        ? (
        <div>
                <div className="bg-blue-600 my-5 hover:opacity-90 duration-300 cursor-pointer text-white p-2 rounded-lg w-96" 
                key = {items[index].id}
                >
                    <h1 className="font-bold text-2xl mb-2">{items[index].title}</h1>
                    <h2 className="mb-3">{items[index].description}</h2>
                    <p className="font-medium text-xl ">R$ {items[index].price}</p>
                </div>

                <div className="w-full flex justify-between">
                    <button onClick={handlePrevious} className="bg-indigo-500 text-white p-2 rounded-lg"> Anterior</button>
                    <button onClick={handleNext} className="bg-indigo-500 text-white p-2 rounded-lg"> Próximo</button>
                </div>
        </div>) 
        : (<div> CLique no botäo para mostrar os itens</div>
        )}
    </section>
  </main>
  );
}
