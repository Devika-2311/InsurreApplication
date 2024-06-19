package com.example.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
@Entity
@Table(uniqueConstraints = {
	    @UniqueConstraint(columnNames = {"userPolicyId"})
	})
public class TermClaim {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long termClaimId;
	    @Column(name = "death_proof")
	    private String deathProof;
	    @Column(name = "nominee_proof")
	    private String nomineeProof;
	    @OneToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "userPolicyId")
	    private UserPolicy userPolicy;
		public Long getTermClaimId() {
			return termClaimId;
		}
		public void setTermClaimId(Long termClaimId) {
			this.termClaimId = termClaimId;
		}
		public String getDeathProof() {
			return deathProof;
		}
		public void setDeathProof(String deathProof) {
			this.deathProof = deathProof;
		}
		public String getNomineeProof() {
			return nomineeProof;
		}
		public void setNomineeProof(String nomineeProof) {
			this.nomineeProof = nomineeProof;
		}
		public UserPolicy getUserPolicy() {
			return userPolicy;
		}
		public void setUserPolicy(UserPolicy userPolicy) {
			this.userPolicy = userPolicy;
		}
		@Override
		public String toString() {
			return "TermClaim [termClaimId=" + termClaimId + ", deathProof=" + deathProof + ", nomineeProof="
					+ nomineeProof + ", userPolicy=" + userPolicy + "]";
		}
		public TermClaim(Long termClaimId, String deathProof, String nomineeProof, UserPolicy userPolicy) {
			super();
			this.termClaimId = termClaimId;
			this.deathProof = deathProof;
			this.nomineeProof = nomineeProof;
			this.userPolicy = userPolicy;
		}
		public TermClaim() {
			super();
		}
 

}
