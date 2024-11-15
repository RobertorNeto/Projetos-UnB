class Stack:
    def __init__(self):
        self.items = []

    def isEmpty(self):
        return self.items == []

    def push(self, item):
        self.items.append(item)

    def pop(self):
        if not self.isEmpty():
            return self.items.pop()

    def peek(self):
        return self.items[-1]

    def size(self):
        return len(self.items)

class Fila_comPilhas:

    def __init__(self):
        self.pilha1 = Stack()
        self.pilha2 = Stack()

    def enqueue(self, item):
        self.pilha1.push(item)

    def dequeue(self):
        tam = self.pilha1.size()

        for _ in range(tam-1):
           self.pilha2.push(self.pilha1.pop())

        aux = self.pilha1.pop()

        for _ in range(tam-1):
            self.pilha1.push(self.pilha2.pop())

        return aux 

    def imprime(self):
        tam2 = self.pilha1.size()

        for _ in range(tam2):
            self.pilha2.push(self.pilha1.pop())
        
        for _ in range(tam2):
            aux = self.pilha2.pop()
            print(aux)
            self.pilha1.push(aux)

    def peek(self):
        return self.pilha1.peek()
    
    def size(self):
        return self.pilha1.size()
    
    def isEmpty(self):
        return self.pilha1.isEmpty()
