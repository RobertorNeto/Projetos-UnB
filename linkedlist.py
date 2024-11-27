class Node:
    def __init__(self, initdata):
        self.data = initdata
        self.next = None

    def getData(self):
        return self.data

    def getNext(self):
        return self.next

    def setData(self, newdata):
        self.data = newdata

    def setNext(self,newnext):
        self.next = newnext

class Linked_List:
    def __init__(self):
        self.head = None
    
    def isEmpty(self):
        return self.head == None
    
    def size(self):
        current = self.head
        count = 0
        while current != None:
            count = count + 1
            current = current.getNext()
        return count

    def imprimir(self):
        atual = self.head
        s = ""
        while atual != None:
            s = s + " " + str(atual.getData())
            atual = atual.getNext() 
        print(s[1:]) 
    
    def addInicio(self,item):
        temp = Node(item)
        temp.setNext(self.head)
        self.head = temp
    
    def addFinal(self,item):
        temp = Node(item)
        if self.isEmpty():
            self.head = temp
        else:
            ultimo = self.head
            while ultimo.getNext() != None:
                ultimo = ultimo.getNext()
            ultimo.setNext(temp)

    def remove(self,item):
        current = self.head
        previous = None
        found = False

        if current is None:
            print("A lista está vazia.")
            return


        while current != None and not found:
            if current.getData() == item:
                found = True
            else:
                previous = current
                current = current.getNext()

        if not found:
            print(f"Item {item} não encontrado na lista.")
            return 
        
        if previous == None:
            self.head = current.getNext()
        else:
            previous.setNext(current.getNext())

    def buscar(self,item):
        current = self.head
        found = False

        while current != None and not found:
            if current.getData() == item:
                found = True
            else:
                current = current.getNext()

        if not found:
            print(f"Item {item} não encontrado na lista.")
            return
        return found
