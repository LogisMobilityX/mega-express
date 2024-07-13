## 트랜잭션 코드 분리
메소드 분리  
``` java
public void upgradeLevels(){
    
    TransactionStatus = this.transactionManager.getTransaction(new DefaultTransactionDefinion());
    
    try {
    
    } catch(Exception e) {
    
    }
}
```  
D.I를 이용한 클래스 분리
